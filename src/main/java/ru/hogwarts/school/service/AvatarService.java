package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;

import static java.lang.String.format;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    @Value("${path.to.avatars}")
    private String avatarDir;
    private final String SERVICE = "Avatar Service Class";
    private final String PATTERN = "Method %s of %s was invoked";

    public Collection<Avatar> getAll (Integer pageNum, Integer pageSize) {
        log.info(format(PATTERN, "getAll", SERVICE));
        return avatarRepository.findAll(PageRequest.of(pageNum, pageSize)).getContent();
    }

    public void uploadAvatar (Long studentId, MultipartFile avatarFile) {
        log.info(format(PATTERN, "uploadAvatar", SERVICE));
        Student student = studentRepository.findById(studentId).orElseThrow();
        Path filePath = Path.of(avatarDir,
                studentId + "." + getExtension(Objects.requireNonNull(avatarFile.getOriginalFilename())));
        try {
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
            try (InputStream inputStream = avatarFile.getInputStream();
                 OutputStream outputStream = Files.newOutputStream(filePath, CREATE_NEW);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024)) {
                bufferedInputStream.transferTo(bufferedOutputStream);
            }
            Avatar avatar = getAvatar(studentId);
            avatar.setStudent(student);
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(avatarFile.getSize());
            avatar.setMediaType(avatarFile.getContentType());
            avatar.setData(prepareAvatar(filePath));
            avatarRepository.save(avatar);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Avatar getAvatar (Long id) {
        log.info(format(PATTERN, "getAvatar", SERVICE));
        return avatarRepository.findByStudentId(id).orElse(new Avatar());
    }

    private String getExtension (String filename) {
        log.info(format(PATTERN, "getExtension", SERVICE));
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    byte[] prepareAvatar (Path filePath) {
        log.info(format(PATTERN, "prepareAvatar", SERVICE));
        try (InputStream inputStream = Files.newInputStream(filePath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bufferedInputStream);
            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();
            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

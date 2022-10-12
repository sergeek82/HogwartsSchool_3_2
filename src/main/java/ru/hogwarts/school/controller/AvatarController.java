package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

/**
 * It is a controller to manipulate image data for student avatars
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    /**
     * Uploading image and binding to student by id
     *
     * @param studentId student id whose gonna attach an image
     */
    @PostMapping(value = "/{studentId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar (@PathVariable Long studentId, @RequestParam MultipartFile avatar) {
        if (avatar.getSize() > 1024 * 300) {
            ResponseEntity.badRequest().body("File is too large...");
        }
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    /**
     * Downloading image from DB by id
     *
     * @param id student id whose photo wil be downloaded
     */
    @GetMapping(value = "/{id}/downloadFromPostgres")
    public ResponseEntity<byte[]> downloadFromDb (@PathVariable Long id) {
        Avatar avatar = avatarService.getAvatar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    /**
     * Downloading image from a local  DISK by id
     *
     * @param id student id whose photo wil be downloaded
     */
    @GetMapping(value = "/{id}/downloadFromDisk")
    public void downloadFromFolder (@PathVariable(value = "id") Long id,
                                    HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.getAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream inputStream = Files.newInputStream(path);
             OutputStream outputStream = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            inputStream.transferTo(outputStream);
        }
    }

    /**
     * Paging
     *
     * @param page page number
     * @param size objects oj the page
     */
    @GetMapping("/byPage")
    public Collection<Avatar> findAllByPage (@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return avatarService.getAll(page - 1, size);
    }
}

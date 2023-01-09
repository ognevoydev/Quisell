package com.ognevoydev.quisell.controller;

import com.ognevoydev.quisell.common.exception.HttpStatusException;
import com.ognevoydev.quisell.model.Post;
import com.ognevoydev.quisell.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
@Tag(name = "Объявления")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить объявление по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно")
    @ApiResponse(responseCode = "404", description = "Не найдено")
    public Post getPostById(
            @Parameter(description = "ID объявления")
            @PathVariable(value = "id") UUID postId) {
        return postService.getPostById(postId);
    }

    @GetMapping
    @Operation(summary = "Получить список всех активных объявлений")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    @Operation(summary = "Создать объявление")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно")
    public void addPost(@RequestBody Post post) {
        postService.savePost(post);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить объявление по id")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно")
    @ApiResponse(responseCode = "403", description = "Доступ запрещён")
    @ApiResponse(responseCode = "404", description = "Не найдено")
    public void deletePost(
            @Parameter(description = "ID объявления")
            @PathVariable(value = "id") UUID postId, Principal principal) {

        if(postService.isPostOwner(postId, UUID.fromString(String.valueOf(principal)))) {
            postService.deletePostById(postId);
        }
        else {
            throw new HttpStatusException("Access to this resource on the server is denied", FORBIDDEN);
        }
    }

}

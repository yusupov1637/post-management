package com.company.service;

import com.company.dto.PostDTO;
import com.company.dto.ProfileDTO;
import com.company.entity.Post;
import com.company.entity.Profile;
import com.company.mapper.ProfilePostMapper;
import com.company.repository.PostRepository;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    ProfileRepository profileRepository;

   @Autowired
   private ProfileService profileService;

    public ResponseEntity<?> createPost(PostDTO postDTO) {
        Post post = dtoToEntity(postDTO);
        postRepository.save(post);
        return ResponseEntity.ok().build();
    }

    public Page<Post> getAllPost(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Post> pageObj = postRepository.findAll(paging);

        List<Post> list = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();

        List<PostDTO> listdto = new ArrayList<>();
        for (Post post : list) {
            listdto.add(entityDot(post));
        }

        Page<Post> response = new PageImpl(listdto, paging, totalElements);
        // return new EmployeePageResponseDTO(dtoList, totalElements);
        return response;

    }
    public List<ProfilePostMapper> getList(Long profile_id){
        List<ProfilePostMapper> profileAndPost = postRepository.getProfileAndPost(profile_id);
    return profileAndPost;
    }

    public List<ProfilePostMapper> getListNAmeTitle(Long profile_id){
        List<ProfilePostMapper> profileAndPost = postRepository.getPNameTitle(profile_id);
    return profileAndPost;
    }
    public PostDTO getTitleCreated(Long id){
        PostDTO postDTO = entityDot(postRepository.getPostById(id));
        return postDTO;
    }
    public List<Profile> get5LastProfile(Long id){
        List<Profile> profiles = profileRepository.findlast5PostsProfile(id);
        return profiles;
    }
    public Integer getCountPostProfile(Long profile_id){
        Integer count = postRepository.getCount(profile_id);
        return count;
    }
    public List<PostDTO> getTodaysPosts(LocalDate today){
        List<Post> postByCreatedDateToday = postRepository.findByCreatedDate(today);
        List<PostDTO> postDTOList=new ArrayList<>();
        for (Post post : postByCreatedDateToday) {
            PostDTO postDTO = entityDot(post);
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }

    public Post dtoToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setProfile(profileService.dtoToEntity(postDTO.getProfileDTO()));
        post.setCreatedDate(LocalDateTime.now());
        return post;
    }

    public PostDTO entityDot(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setTitle(post.getTitle());
        postDTO.setProfileDTO(profileService.entityDto(post.getProfile()));
        postDTO.setCreatedDate(post.getCreatedDate());
        return postDTO;
    }
}

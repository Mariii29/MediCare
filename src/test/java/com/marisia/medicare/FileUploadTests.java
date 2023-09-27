package com.marisia.medicare;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.marisia.medicare.service.storage.StorageFileNotFoundException;
import com.marisia.medicare.service.storage.StorageService;

@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private StorageService storageService;

  @WithMockUser
  public void shouldSaveUploadedFile() throws Exception {
    var originalFilename = "myImage.txt";
    MockMultipartFile multipartFile = new MockMultipartFile("file", originalFilename,
        "text/plain", "Spring Framework".getBytes());

    then(this.storageService).should().store(multipartFile, originalFilename);
  }

  @Test
  @WithMockUser
  public void should404WhenMissingFile() throws Exception {
    given(this.storageService.loadAsResource("test.txt"))
        .willThrow(StorageFileNotFoundException.class);

    this.mvc.perform(get("/files/test.txt")).andExpect(status().isNotFound());
  }

}
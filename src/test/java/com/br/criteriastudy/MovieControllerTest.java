package com.br.criteriastudy;

import java.time.LocalDate;

import com.br.criteriastudy.entities.CategoryEnum;
import com.br.criteriastudy.repositories.criteria.params.MovieFilterParam;
import com.br.criteriastudy.services.DTO.MovieDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    public MovieControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    public void successWithGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movie")
            .contentType("application/json"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void successGetWithParams() throws Exception {
        MovieFilterParam param = new MovieFilterParam();
        param.setCategory(CategoryEnum.ACTION);

        mockMvc.perform(MockMvcRequestBuilders.get("/movie", objectMapper.writeValueAsString(param))
            .contentType("application/json"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void successWithPost() throws Exception {
        MovieDTO dto = new MovieDTO();
        dto.setReleaseDate(LocalDate.of(2020, 10, 20));
        dto.setCategory(CategoryEnum.ACTION);
        dto.setSinopse("Test sinopse");
        dto.setTitle("Random title");
        dto.setDirector("Scorsese");

        mockMvc.perform(MockMvcRequestBuilders.post("/movie")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
}
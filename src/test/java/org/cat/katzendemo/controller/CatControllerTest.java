package org.cat.katzendemo.controller;

import org.cat.katzendemo.model.Breed;
import org.cat.katzendemo.model.Cat;
import org.cat.katzendemo.model.CatRepository;
import org.cat.katzendemo.model.HabitRepository;
import org.cat.katzendemo.service.CatService;
import org.cat.katzendemo.service.HabitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest({CatController.class})
@AutoConfigureMockMvc(addFilters = false)
class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatRepository catRepository;

    @MockBean
    private CatService catService;

    @MockBean
    private HabitRepository habitRepository;

    @MockBean
    private HabitService habitService;


    Cat newCat;

    List<Cat> cats;

    @BeforeEach
    void setUp() {
        // list mock
        cats = List.of(
                new Cat("Muppi", Breed.MAINE_COON, 6.7f),
                new Cat("Brathänchen", Breed.DEVON_REX, 4.5f),
                new Cat("Knut", Breed.BIRMAN, 3.7f)
        );
        Page<Cat> catPage = new PageImpl<Cat>(cats, Pageable.unpaged(), cats.size());

        Mockito.when(catService.getAll(any())).thenReturn(catPage);
        Mockito.when(catRepository.findAll(any(Pageable.class))).thenReturn(catPage);

        // create mock
        newCat = new Cat("Mr. Schmauntz", Breed.BENGAL, 4.5f);
        newCat.setId(1L);
        Mockito.when(catService.create(any())).thenReturn(newCat);
        Mockito.when(catRepository.save(any())).thenReturn(newCat);
    }

    @Test
    void getById() {
    }

    @Test
    void getCats() throws Exception {

        String expectedJson = """
                {
                   "content": [
                     {
                       "id": null,
                       "name": "Muppi",
                       "breed": "MAINE_COON",
                       "weight": 6.7,
                       "habits": null
                     },
                     {
                       "id": null,
                       "name": "Brathänchen",
                       "breed": "DEVON_REX",
                       "weight": 4.5,
                       "habits": null
                     },
                     {
                       "id": null,
                       "name": "Knut",
                       "breed": "BIRMAN",
                       "weight": 3.7,
                       "habits": null
                     }
                   ],
                   "pageable": "INSTANCE",
                   "last": true,
                   "totalPages": 1,
                   "totalElements": 3,
                   "size": 3,
                   "number": 0,
                   "sort": {
                     "empty": true,
                     "unsorted": true,
                     "sorted": false
                   },
                   "first": true,
                   "numberOfElements": 3,
                   "empty": false
                }
                """;

        mockMvc.perform(
                        get("/cat")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

    }

    @Test
    void getCatsByBreed() {
    }

    @Test
    void createCat() throws Exception {
        String json = """
                        {
                        	"name": "Mr. Schmauntz",
                        	"breed": "BENGAL",
                        	"weight": 4.5
                        }
                """;

        mockMvc.perform(
                        post("/cat")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Mr. Schmauntz"))
                .andExpect(jsonPath("$.breed").value("BENGAL"))
                .andExpect(jsonPath("$.weight").value(4.5f));
    }
}
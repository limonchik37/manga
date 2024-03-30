package com.example.MangaHaven.conroller;

import com.example.MangaHaven.dto.MangaDto;
import com.example.MangaHaven.entity.Manga;
import com.example.MangaHaven.mapper.MangaMapper;
import com.example.MangaHaven.service.MangaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MangaTestController {
    private MockMvc mockMvc;

    @MockBean
    private MangaService mangaService;

    @MockBean
    private MangaMapper mangaMapper;

    @Test
    public void testCreateCustomer() throws Exception {
        MangaDto mangaDto = new MangaDto(1L, "Kimetsu no yaiba", "Alim", "just interesting title", null, null, null , null );
        Manga manga = new Manga(1L, "Kimetsu no yaiba", "Alim", "just interesting title", null, null, null);

        when(mangaMapper.toEntity(any(MangaDto.class))).thenReturn(manga);
        when(mangaService.create(any(MangaDto.class))).thenReturn(manga);
        when(mangaMapper.toDto(any(Manga.class))).thenReturn(mangaDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/mangas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Kimetsu no yaiba\",\"author\":\"Alim\",\"description\":\"just interesting title\",\"chapters\":null,\"coverImage\":null}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Kimetsu no yaiba"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Alim"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("just interesting titlem"));
        Mockito.verify(mangaService).create(mangaDto);
    }

    @Test
    public void testMangaWithcChaptersandImage() throws Exception {

    }

}

//
//    @Test
//    public void testCustomerWithReservations() throws Exception {
//        Reservation reservation1 = Reservation.builder()
//                .id(1L)
//                .checkInDate(LocalDate.of(2024, 1, 10))
//                .checkOutDate(LocalDate.of(2024, 1, 15))
//                .numberOfGuests(2)
//                .status("Booked")
//                .build();
//
//        Reservation reservation2 = Reservation.builder()
//                .id(2L)
//                .checkInDate(LocalDate.of(2024, 2, 5))
//                .checkOutDate(LocalDate.of(2024, 2, 10))
//                .numberOfGuests(3)
//                .status("Confirmed")
//                .build();
//
//        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
//
//        Customer customerWithReservations = new Customer(1L, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", reservations);
//
//        List<Long> reservationIds = Arrays.asList(1L, 2L);
//        CustomerDTO customerDTOWithReservations = new CustomerDTO(1L, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", reservationIds);
//
//        when(customerService.readById(1L)).thenReturn(customerWithReservations);
//        when(customerMapper.toDto(customerWithReservations)).thenReturn(customerDTOWithReservations);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationIds").isArray())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationIds", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationIds[0]").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationIds[1]").value(2L));
//    }
//
//    @Test
//    public void testCustomerWithoutReservations() throws Exception {
//        CustomerDTO customerDTOWitoutReservations = new CustomerDTO(2L, "Jane", "Doe", "0987654321", "jane.doe@example.com", "456 Main St", Collections.emptyList());
//        Customer customerWithoutReservations = new Customer(2L, "Jane", "Doe", "0987654321", "jane.doe@example.com", "456 Main St", Collections.emptyList());
//
//        when(customerService.readById(2L)).thenReturn(customerWithoutReservations);
//        when(customerMapper.toDto(customerWithoutReservations)).thenReturn(customerDTOWitoutReservations);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/customers/2"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationIds").isEmpty());
//    }
//
//    @Test
//    public void testFindCustomerByEmailSuccess() throws Exception {
//        String email = "john.doe@example.com";
//        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "Doe", "1234567890", email, "123 Main St", null);
//        Customer customer = new Customer(1L, "John", "Doe", "1234567890", email, "123 Main St", new ArrayList<>());
//
//        when(customerService.readByCustomerEmail(email)).thenReturn(customer);
//        when(customerMapper.toDto(customer)).thenReturn(customerDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/customers/email/" + email))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email));
//    }
//
//    @Test
//    public void testFindCustomerByEmailNotFound() throws Exception {
//        String email = "unknown@example.com";
//
//        when(customerService.readByCustomerEmail(email)).thenThrow(new EntityDoesNotExistException(""));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/customers/email/" + email))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }

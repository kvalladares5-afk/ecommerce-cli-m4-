package com.ejemplo;

import com.ejemplo.modelo.Usuario;
import com.ejemplo.servicio.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void testAccesoRestringidoAdmin() throws Exception {
        mockMvc.perform(get("/admin/productos"))
               .andExpect(status().is3xxRedirection());
    }

    @Test
    void testEncriptacionPasswordUsuario() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Test");
        nuevoUsuario.setApellido("Unitario");
        nuevoUsuario.setEmail("test@prueba.com");
        nuevoUsuario.setPassword("clave123"); 

        Usuario usuarioGuardado = usuarioService.registrarUsuario(nuevoUsuario);

        assertNotEquals("clave123", usuarioGuardado.getPassword());
        assertTrue(usuarioGuardado.getPassword().startsWith("$2a$")); 
    }
}
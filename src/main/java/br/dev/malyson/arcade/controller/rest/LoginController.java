package br.dev.malyson.arcade.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import br.dev.malyson.arcade.config.JwtTokenUtil;
import br.dev.malyson.arcade.dto.auth.LoginDTO;
import br.dev.malyson.arcade.dto.auth.RegistrarDTO;
import br.dev.malyson.arcade.service.auth.LoginService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class LoginController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenUtil jUtil;

    @PostMapping("/login")
    public String doLogin(@RequestBody LoginDTO loginDTO) {
        return autenticar(loginDTO);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar (@RequestBody RegistrarDTO regDTO) {
        return loginService.registrar(regDTO);
    }
    
    private String autenticar(LoginDTO loginDTO){
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getLogin(),
                loginDTO.getSenha()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails user = loginService.loadUserByUsername(loginDTO.getLogin());
        String token = jUtil.generateToken(user);
        return token;
    }
}

package br.dev.malyson.arcade.controller.mvc;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.dev.malyson.arcade.dto.auth.RegistrarDTO;
import br.dev.malyson.arcade.model.auth.Usuario;
import br.dev.malyson.arcade.repository.auth.UsuarioRepository;
import br.dev.malyson.arcade.service.auth.LoginService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @Autowired
    UsuarioRepository uRepo;

    @Autowired
    LoginService lService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @GetMapping("player")
    public ModelAndView player(){
        ModelAndView mav = new ModelAndView("emu/Player");
        return mav;
    }

    @GetMapping("/success")
    public ModelAndView sucesso(Principal auth){
        ModelAndView mav = new ModelAndView("Success");
        return mav;
    }

    /*@GetMapping("/registrar")
    public ModelAndView registrarForm() {
        ModelAndView mav = new ModelAndView("registrar");
        mav.addObject("user", new RegistrarDTO());
        return mav;
    }*/

    @PostMapping("/registrar")
    public String registrar(RegistrarDTO dto) {
        lService.registrar(dto);
        return "forward:/?success=true";
    }

    @GetMapping("/alterarUser")
    public ModelAndView alterarForm() {
        ModelAndView mav = new ModelAndView("alterarUsuario");
        mav.addObject("user", new RegistrarDTO(LoginService.getUser()));
        return mav;
    }
    
    @PostMapping("/alterarUser/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id, RegistrarDTO dto) {
        lService.alterarUsuario(id, dto);
        return new ModelAndView("redirect:/success");
    }

    @PostMapping("/loginProcess")
    public String login(Principal auth) throws Exception {
        if(auth == null){
            throw new Exception("Usuário não autenticado.");
        }
        autenticar(auth);
        return "forward:/";
    }

    private Usuario autenticar(Principal auth) throws Exception{
        Usuario user = uRepo.findByLogin(auth.getName()).orElseThrow(() -> new Exception("Usuário não existe"));
        Authentication reAuth = new UsernamePasswordAuthenticationToken(auth.getName(), user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(reAuth);
        return user;
    }

}

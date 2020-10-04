package curso.springboot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Usuario;
import curso.springboot.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@RequestMapping(method = RequestMethod.GET, value = "**/cadastrousuario")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrousuario");
		modelAndView.addObject("usuarioobj", new Usuario());
		return modelAndView;
	}
	

	@RequestMapping(method = RequestMethod.POST, 
			value = "**/salvarusuario")
	public ModelAndView salvar(@Valid Usuario usuario, 
			   BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastrousuario");
			modelAndView.addObject("usuarios", usuarioRepository.findAll());
			modelAndView.addObject("usuarioobj", usuario);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		usuarioRepository.save(usuario);
		/*
		ModelAndView andView = new ModelAndView("cadastro/cadastrousuario");
		andView.addObject("usuarios", usuarioRepository.findAll());
		andView.addObject("usuarioobj", new Usuario());
		return andView;
		*/
		
		ModelAndView andView = new ModelAndView("usuariopage");
		
		return andView;

	}

}

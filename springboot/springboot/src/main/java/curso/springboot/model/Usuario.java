package curso.springboot.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String login;
	private String senha;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_role", 
	     joinColumns = @JoinColumn(name = "usuario_id", 
	                   referencedColumnName = "id",
	                   table = "usuario"),  // cria tabela de acesso do usuário
			
			inverseJoinColumns = @JoinColumn(name="role_id",
								referencedColumnName = "id",
								table = "role"))
	
	private List<Role> roles;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="usuario_projeto",
			joinColumns={@JoinColumn(name="usuario_id")},
			inverseJoinColumns={@JoinColumn(name="projeto_id")})
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	
	/*
	 * ao criar novo usuario, criar nova usuarios_role 
	 * public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public Usuario() {
	}
	
	
	
	
	 */
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCripto = encoder.encode(senha);// seta uma senha criptografada
		this.senha = senhaCripto;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}

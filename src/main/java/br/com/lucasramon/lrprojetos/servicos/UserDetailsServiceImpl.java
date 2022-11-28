package br.com.lucasramon.lrprojetos.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lucasramon.lrprojetos.entidades.Funcionario;
import br.com.lucasramon.lrprojetos.entidades.UserDetailsImpl;
import br.com.lucasramon.lrprojetos.repositorios.FuncionarioRepositorio;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepositorio.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UserDetailsImpl(funcionario);
    }
    
}

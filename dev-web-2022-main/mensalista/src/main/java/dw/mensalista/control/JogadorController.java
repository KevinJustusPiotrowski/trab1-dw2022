package dw.mensalista.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.mensalista.model.Jogador;
import dw.mensalista.repository.JogadorRepository;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping("")
    public ResponseEntity<List<Jogador>> listar(@RequestParam(required = false) String nome) {
        try {
            List<Jogador> jogadores = new ArrayList<Jogador>();

            if (nome == null)
                this.jogadorRepository.findAll().forEach(jogadores::add);
            else
                this.jogadorRepository.findByNomeContaining(nome).forEach(jogadores::add);

            if (jogadores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(jogadores, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Jogador> criar(@RequestBody Jogador jogador) {
        try {
            Jogador novoJogador = this.jogadorRepository.save(new Jogador(jogador.getNome(), jogador.getEmail(), jogador.getDataNasc()));
            
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAll()
    {
        try {
            this.jogadorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> show(@PathVariable long id) {
        Optional<Jogador> data = this.jogadorRepository.findById(id);

        if (data.isPresent())
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            this.jogadorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> editar(@PathVariable("id") long id, @RequestBody Jogador novoJogador) {

        Optional<Jogador> data = this.jogadorRepository.findById(id);

        if (data.isPresent()) {
           Jogador jogador = data.get();
           jogador.setNome(novoJogador.getNome());
           jogador.setDataNasc(novoJogador.getDataNasc());
           jogador.setEmail(novoJogador.getEmail());

           return new ResponseEntity<>(this.jogadorRepository.save(jogador), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

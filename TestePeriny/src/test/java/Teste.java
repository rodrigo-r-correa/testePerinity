
import com.testePeriny.entity.Pessoa;
import com.testePeriny.entity.Tarefa;
import com.testePeriny.repository.PessoaRepository;
import com.testePeriny.repository.TarefaRepository;
import com.testePeriny.service.TarefaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarefaServiceTest {

    @InjectMocks
    private TarefaService tarefaService; 

    @Mock
    private TarefaRepository tarefaRepository; 

    @Mock
    private PessoaRepository pessoaRepository; 

    private Tarefa tarefa;
    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Jo�o");
        pessoa.setDepartamento("TI");

        tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("Tarefa 1");
        tarefa.setDescricao("Descri��o da Tarefa 1");
        tarefa.setPrazo(LocalDate.now().plusDays(5));
        tarefa.setDepartamento("TI");
        tarefa.setDuracao(10);
        tarefa.setFinalizada(false);
    }

    @Test
    void testAdicionarTarefa() {
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa novaTarefa = tarefaService.adicionarTarefa(tarefa);

        assertNotNull(novaTarefa);
        assertEquals("Tarefa 1", novaTarefa.getTitulo());
        assertEquals("Descri��o da Tarefa 1", novaTarefa.getDescricao());

        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void testAlocarPessoaNaTarefa() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Tarefa tarefaAlocada = tarefaService.alocarPessoaNaTarefa(1L, pessoa);

        assertNotNull(tarefaAlocada.getPessoa());
        assertEquals("Jo�o", tarefaAlocada.getPessoa().getNome());
        assertEquals("TI", tarefaAlocada.getPessoa().getDepartamento());

        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void testFinalizarTarefa() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        Tarefa tarefaFinalizada = tarefaService.finalizarTarefa(1L);

        assertTrue(tarefaFinalizada.isFinalizada());

        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }
}

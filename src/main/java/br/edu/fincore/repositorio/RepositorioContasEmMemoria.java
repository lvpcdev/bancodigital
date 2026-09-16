package br.edu.fincore.repositorio;
import br.edu.fincore.modelo.Conta;
import java.util.*;
public class RepositorioContasEmMemoria {
    private final Map<String, Conta> dados = new HashMap<>();
    public void salvar(Conta a){ dados.put(a.numero,a); }
    public Conta buscar(String n){ return dados.get(n); }
    public Collection<Conta> listarTodos(){ return dados.values(); }
}

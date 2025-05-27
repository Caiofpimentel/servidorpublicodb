package com.caiofpimentel.servidorpublicodb;

import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.caiofpimentel.servidorpublicodb.entity.ServidorPublico;
import com.caiofpimentel.servidorpublicodb.service.ServidorPublicoService;

import jakarta.annotation.PostConstruct;

@Configuration
public class ServidorpublicoApplicationRunner implements CommandLineRunner {

	private ServidorPublicoService servidorService;

	@Autowired
	public void setServidorPublicoService(ServidorPublicoService servidorService) {
		this.servidorService = servidorService;
	}

	
	public void listAll() {
		List<ServidorPublico> servidorespublicos = servidorService.findAll();

		if (servidorespublicos.size() != 0) {
			System.out.println("###################################");
			servidorespublicos.forEach(
					servidor -> {
						System.out.println(servidor.getMatricula());
						System.out.println(servidor.getNome());
						System.out.println(servidor.getFoto());
						System.out.println(servidor.getOrgao());
					});

		} else {
			System.out.println("Tabela sem registros");
			System.out.println("###################################");
		}
	}

	
	public void listByMatricula() {
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matrícula procurada:"));
		Optional<ServidorPublico> servidorEncontrado = servidorService.findByMatricula(matricula);

		if (servidorEncontrado.isPresent()) {
			System.out.println("###################################");
			System.out.println(servidorEncontrado.get().getMatricula());
			System.out.println(servidorEncontrado.get().getNome());
			System.out.println(servidorEncontrado.get().getFoto());
			System.out.println(servidorEncontrado.get().getOrgao());
		} else
			System.out.println("Não existe o servidor público com a matrícula informada");

	}
	
	public void saveServidorPublico() {
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite o novo servidor público:"));
		Optional<ServidorPublico> servidorEncontrado = servidorService.findByMatricula(matricula);

		if (servidorEncontrado.isPresent()) {
			System.out.println("Já existe um servidor público com a matrícula informada!");
			return;
		}else{
			ServidorPublico novoServidor = new ServidorPublico();
			novoServidor.setMatricula(matricula);
			novoServidor.setNome(JOptionPane.showInputDialog("Digite o nome do servidor público"));
			novoServidor.setFoto(JOptionPane.showInputDialog("Digite a foto do servidor público"));
			novoServidor.setOrgao(JOptionPane.showInputDialog("Digite o órgão do servidor público"));
			novoServidor.setVinculo(JOptionPane.showInputDialog("dIgite o vínculo do servidor público"));
			novoServidor.setCargo(JOptionPane.showInputDialog("Digite o cargo do servidor público"));
			novoServidor.setLotacao(JOptionPane.showInputDialog("Digite a lotação do servidor público"));
			novoServidor.setEmail(JOptionPane.showInputDialog("Digite o email do servidor público"));
			novoServidor.setTelefone(JOptionPane.showInputDialog("Digite o telefone do servidor público"));
			novoServidor.setNaturalidade(JOptionPane.showInputDialog("Digite a naturalidade do servidor público"));
			novoServidor.setCpf(JOptionPane.showInputDialog("Digite o CPF do servidor público"));
			novoServidor.setCelular(JOptionPane.showInputDialog("Digite o celular do servidor público"));
			novoServidor.setExercicio(JOptionPane.showInputDialog("Digite o exercício do servidor público"));

			servidorService.save(novoServidor);
			System.out.println("Servidor público salvo com sucesso!");
		}
		
	}
	
	public void updateServidorPublico() {
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matrícula do servidor público a ser alterado:"));
		Optional<ServidorPublico> servidorEncontrado = servidorService.findByMatricula(matricula);

		if(servidorEncontrado.isPresent()){
			servidorEncontrado.get().setNome(JOptionPane.showInputDialog("Digite o nome do servidor público"));
			servidorService.update(servidorEncontrado.get());
			System.out.println("Servidor público atualizado com sucesso!");

		}else{
			System.out.println("Não existe o servidor público com a matrícula informada");
			return;
		}
	}
	@PostConstruct
	public void deleteServidorPublico() {
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matrícula do servidor público a ser excluído:"));
		Optional<ServidorPublico> servidorEncontrado = servidorService.findByMatricula(matricula);

		if(servidorEncontrado.isPresent()){
			servidorService.delete(matricula);
			System.out.println("Servidor público excluído com sucesso!");
		}else{
			System.out.println("Não existe o servidor público com a matrícula informada");
			return;
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}

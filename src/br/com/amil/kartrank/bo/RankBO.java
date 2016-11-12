package br.com.amil.kartrank.bo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.amil.kartrank.util.DateUtil;
import br.com.amil.kartrank.vo.Piloto;
import br.com.amil.kartrank.vo.Rank;
import br.com.amil.kartrank.vo.Volta;


public class RankBO {
	//CRITÉRIOS DE ORDENAÇÃO E FILTRAGEM
	private Function<Volta, Integer> ordenarPorVolta  = v -> v.getNumeroVolta();
	private Function<Volta, LocalTime> ordenarPorHora = v -> v.getHora();
	private Function<Volta, LocalTime> tempoDaVolta   = v -> v.getTempo();
	
	
	public List<Rank> criarRank(List<Volta> voltas) throws Exception{
		ordenarVoltas(voltas);
		
		List<Piloto> pilotos = getPilotos(voltas);
		List<Rank> rankList = new ArrayList<>();
		
		int posicaoDoPiloto = 1;
		
		for(Piloto piloto : pilotos){
			Rank rank = new Rank();
			
			rank.setPosicao(posicaoDoPiloto);
			rank.setPiloto(piloto);
			
			int voltasCompletadas = voltas.stream()
					.filter(v -> v.getPiloto().getId().equals(piloto.getId()))
					.mapToInt(v -> v.getNumeroVolta())
					.findFirst()
					.getAsInt();
			
			rank.setTotalVoltas(voltasCompletadas);
			
			// CALCULO DE TEMPO TOTAL DE CADA PILOTO
			List<LocalTime> temposDeCorrida = voltas.stream()
					.filter(v -> v.getPiloto().getId().equals(piloto.getId()))
					.map(volta -> volta.getTempo())
					.collect(Collectors.toList());
			
			rank.setTempoTotal(DateUtil.plusTime(temposDeCorrida));
			
			//MELHOR VOLTA DE CADA PILOTO
			Volta melhorVolta =  voltas.stream()
					.filter(v -> v.getPiloto().getId().equals(piloto.getId()))
					.min(Comparator.comparing(tempoDaVolta))
					.get();
			
			rank.setMelhorVoltaCadaPiloto(melhorVolta.getTempo());
			
			// VELOCIDADE MÉDIA DE CADA PILOTO
			double velocidadeMediaNaCorrida =  voltas.stream()
					.filter(v -> v.getPiloto().getId().equals(piloto.getId()))
					.mapToDouble(vm -> vm.getVelocidadeMedia())
					.summaryStatistics()
					.getAverage();
			
			rank.setVelocidadeMediaNaCorrida(velocidadeMediaNaCorrida);
			
			posicaoDoPiloto++;
			rankList.add(rank);			
		}
		
		//TEMPO DE CADA PILOTO DEPOS DO VENCEDOR
		Map<String, LocalTime> temposDepoisDoVencedor = getTempoDepoisDoVencedor(voltas);
		
		for(String idPiloto : temposDepoisDoVencedor.keySet()){
			Rank rank = rankList.stream()
					.filter(r -> r.getPiloto().getId().equals(idPiloto))
					.findFirst().get();
			
			rank.setTempoAposVencedor(temposDepoisDoVencedor.get(idPiloto));
		}
		
		return rankList;
	}
	
	
	public List<Piloto> getPilotos(List<Volta> voltas){
		//LISTA DE PILOTOS SEM DUPLICIDADE
		List<Piloto> pilotos = voltas.stream()
				.map(v -> v.getPiloto())
				.distinct()
				.collect(Collectors.toList());
		
		return pilotos;
	}
	
	
	public Volta getMelhorVoltaDaCorrida(List<Volta> voltas){
		//MELHOR VOLTA DA CORRIDA
		Volta melhorVoltaDaCorrida = voltas.stream()
				.min(Comparator.comparing(tempoDaVolta))
				.get();
		
		return melhorVoltaDaCorrida;
	}
	
	
	public Piloto getVencedor(List<Volta> voltas){
		Piloto primeiroColocado = voltas.stream()
				.filter(v -> v.getNumeroVolta() == 4)
				.min(Comparator.comparing(ordenarPorHora))
				.get().getPiloto();
		
		return primeiroColocado;
	}
	
	
	private void ordenarVoltas(List<Volta> voltas){
		//ORDENAÇÃO DECRESCENTE POR Nº DE VOLTAS E HORÁRIO 
		voltas.sort(Comparator.comparing(ordenarPorVolta)
				.reversed()
				.thenComparing(ordenarPorHora));
	}
	
	public List<Rank> getTimeAfterWinner(List<Rank> rankList){
		System.out.println("TEMPO APÓS O VENCEDOR \n");
		List<Rank> novoRank = new ArrayList<>();
		for(Rank rank : rankList){
			System.out.println(rank.getTempoAposVencedor());
			if(rank.getTempoAposVencedor() != null){
				novoRank.add(rank);
			}
		}
		return novoRank;
	}
	
	
	private Map<String, LocalTime> getTempoDepoisDoVencedor(List<Volta> voltas){
		Piloto vencedor = getVencedor(voltas);
		List<Piloto> pilotos = getPilotos(voltas);
		
		pilotos.removeIf(p -> p.getId().equals(vencedor.getId()));
		
		Volta voltaVencedor = voltas.stream()
				.filter(v -> v.getPiloto().getId().equals(vencedor.getId()))
				.findFirst().get();
		
		Map<String, LocalTime> temposDepoisDoVencedor = new LinkedHashMap<>();
		
		for(Piloto piloto : pilotos){
			Volta volta = voltas.stream()
					.filter(v -> v.getPiloto().getId().equals(piloto.getId()))
					.findFirst().get();
			
			LocalTime tempo = DateUtil.between(voltaVencedor.getHora(), volta.getHora());
			temposDepoisDoVencedor.put(piloto.getId(), tempo);
		}
		
		return temposDepoisDoVencedor;
	}
}
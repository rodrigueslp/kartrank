package br.com.amil.kartrank.util;

import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import br.com.amil.kartrank.vo.Piloto;
import br.com.amil.kartrank.vo.Volta;


@SuppressWarnings("resource")
public class FileUtil {
	
	public List<Volta> readFile(InputStream inpStream) throws Exception{
		Scanner scanner = new Scanner(inpStream, "UTF-8");
		
		List<Volta> listaVoltas = new ArrayList<>();				
		Integer line = 0;
		
		while (scanner.hasNext()) {
			String[] arr = scanner.nextLine().split(" ");
			List<String> listArr = new ArrayList<>();
			Collections.addAll(listArr, arr);
			
			Integer column = 0;
			Integer countColumnFormat = 1;
			
			if (line > 0) {				
				Volta volta = new Volta();
				Piloto piloto = new Piloto();
				
				for (String col : arr) {
					if (col.equals("")) {
						listArr.remove(column);
					} 
					else {				
						if (countColumnFormat.equals(1)) {
							volta.setHora(LocalTime.from(DateUtil.convertToLocalTime(col, "HH:mm:ss.SSS")));
						} 
						else if(countColumnFormat.equals(2)) {
							piloto.setId(col);
						} 
						else if (countColumnFormat.equals(4)) {							
							piloto.setNome(col);
						} 
						else if (countColumnFormat.equals(5)) {
							volta.setNumeroVolta(Integer.parseInt(col));
						} 
						else if (countColumnFormat.equals(6)) {
							volta.setTempo(DateUtil.convertToLocalTime(col, "mm:ss.SSS"));
						}
						else if (countColumnFormat.equals(7)) {
							volta.setVelocidadeMedia(Double.parseDouble(col.replace(",", ".")));
						}
						
						countColumnFormat++;
					}
					
					column++;
				}			
				
				volta.setPiloto(piloto);
				listaVoltas.add(volta);
			}
		
			line++;
		}
		
		return listaVoltas;
	} 
}
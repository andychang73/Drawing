package com.planto.drawing;

import com.planto.drawing.aggregations.DrawingClientAggService;
import com.planto.drawing.entities.CommandHistoryEntity;
import com.planto.drawing.entities.SymbolEntity;
import com.planto.drawing.services.CommandHistoryService;
import com.planto.drawing.services.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories("com.planto.drawing.*")
public class DrawingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DrawingApplication.class, args);
	}

	private final SymbolService symbolService;
	private final CommandHistoryService historyService;
	private final DrawingClientAggService drawingClientAggService;

	@Autowired
	public DrawingApplication(SymbolService symbolService, CommandHistoryService commandHistoryService, DrawingClientAggService drawingClientAggService) {
		this.symbolService = symbolService;
		this.historyService = commandHistoryService;
		this.drawingClientAggService = drawingClientAggService;
	}

	@PostConstruct
	public void init(){
		symbolService.add(
				SymbolEntity.builder()
						.id(1)
						.symbol('x')
						.build()
		);
		// todo
//		historyService.add(
//				CommandHistoryEntity.builder()
//						.id(1)
//						.command("init")
//						.canvas("")
//						.build()
//		);
	}

	@Override
	public void run(String... args) {
		System.out.println();
		System.out.println();
		System.out.println("Starting Drawing Program...............");
		drawingClientAggService.draw();
	}
}

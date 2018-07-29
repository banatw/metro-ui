package com.example.metroui;

import com.entity.Kota;
import com.entity.Pegawai;
import com.formmodel.PegawaiFormModel;
import com.jsonmodel.OperationResult;
import com.viewmodel.KotaViewModel;
import com.viewmodel.PegawaiViewModel;
import com.repo.KotaRepo;
import com.repo.PegawaiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("com.repo")
@EntityScan("com.entity")
public class MetroUiApplication {

	@Autowired
	private PegawaiRepo pegawaiRepo;

	@Autowired
	private KotaRepo kotaRepo;

	public static void main(String[] args) {
		SpringApplication.run(MetroUiApplication.class, args);
	}

	/*Init Data*/
	@Bean
	public CommandLineRunner commandLineRunner() {
		return(x)->{
			Kota kota1 = new Kota();
			kota1.setNama("Depok");
			kotaRepo.save(kota1);

			Kota kota2 = new Kota();
			kota2.setNama("Jakarta");
			kotaRepo.save(kota2);

			Pegawai pegawai = new Pegawai();
			pegawai.setNama("syabana");
			pegawai.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse("1980-06-17"));
			pegawai.setKota(kota1);
			pegawaiRepo.save(pegawai);

			pegawai = new Pegawai();
			pegawai.setNama("fatih");
			pegawai.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse("2012-04-28"));
			pegawai.setKota(kota2);
			pegawaiRepo.save(pegawai);
		};
	}

	@Controller
	public class MyController {

		@GetMapping("/test")
		public String test() {
			return "test";
		}

		@GetMapping("/home")
		public String showHome() {
			return "home";
		}

		@GetMapping("/pegawai")
		public String showPegawai(Model model) {
			List<PegawaiViewModel> pegawaiModels = new ArrayList<>();
			for(Pegawai p: pegawaiRepo.findAll()) {
				PegawaiViewModel pegawaiModel = new PegawaiViewModel();
				pegawaiModel.setIdPegawai(p.getIdPegawai());
				pegawaiModel.setNama(p.getNama());
				pegawaiModel.setKota(p.getKota().getNama());
				pegawaiModel.setTglLahir(new SimpleDateFormat("dd MMMM yyyy").format(p.getTanggalLahir()));
				pegawaiModels.add(pegawaiModel);
			}
			model.addAttribute("pegawaiModels",pegawaiModels);
			return "m_pegawai";
		}

		@GetMapping("/addpegawai")
		public String addPegawai(Model model) {
			List<KotaViewModel> kotaViewModels = new ArrayList<>();
			for(Kota k : kotaRepo.findAll()) {
				KotaViewModel kotaViewModel = new KotaViewModel();
				kotaViewModel.setIdKota(k.getIdKota());
				kotaViewModel.setNama(k.getNama());
				kotaViewModel.setSelected(false);
				kotaViewModels.add(kotaViewModel);
			}
			model.addAttribute("kotaViewModels",kotaViewModels);
			return "pegawai_form";
		}

		@GetMapping("/github")
		public String github(Model model) {

			return "github";
		}



	}

	@RestController
	public class MyrestController {

		@PostMapping("/simpanpegawai")
		public OperationResult simpanPegawai(@Valid PegawaiFormModel pegawaiInputModel, BindingResult bindingResult, Model
				model) {
			OperationResult operationResult = new OperationResult();
			if(bindingResult.hasErrors()) {
				operationResult.setSuccess(false);
				operationResult.setMessage(bindingResult.getFieldError().getDefaultMessage());
				return operationResult;
			}
			Pegawai pegawai = new Pegawai();
			pegawai.setNama(pegawaiInputModel.getNama());
			try {
				pegawai.setTanggalLahir(new SimpleDateFormat("dd MMMM yyyy").parse(pegawaiInputModel.getTglLahir()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pegawai.setKota(kotaRepo.findOne(Integer.valueOf(pegawaiInputModel.getKota())));
			pegawaiRepo.save(pegawai);
			operationResult.setSuccess(true);
			operationResult.setMessage("");
			return operationResult;
		}
	}
}

package com.example.metroui;

import com.entity.Kota;
import com.entity.Pegawai;
import com.model.submit.PegawaiForm;
import com.model.json.OperationResult;
import com.model.view.KotaView;
import com.model.view.ListPegawaiView;
import com.model.view.PegawaiFormView;
import com.repo.KotaRepo;
import com.repo.PegawaiRepo;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

			pegawai = new Pegawai();
			pegawai.setNama("Rafa");
			pegawai.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse("2015-03-25"));
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

		@GetMapping("/index")
		public String showIndex(Model model) {
			model.addAttribute("index_active",true);
			return "index";
		}

		@GetMapping("/")
		public String showHome() {
			return "redirect:/index";
		}

		@GetMapping("/pegawai")
		public String showPegawai(@RequestParam(name = "p",defaultValue = "1") String sPage,
				@RequestParam(name = "q",defaultValue = "") String query, Model model) {
			Integer iPage = null;
			try {
				iPage = Integer.parseInt(sPage);
				if(iPage<1) iPage=1;
			}
			catch (NumberFormatException nfe) {
				iPage = 1;
			}
			org.springframework.data.domain.Pageable pageable = new PageRequest(iPage-1,2);
			List<PegawaiFormView> pegawaiFormViews = new ArrayList<>();
			for(Pegawai p: pegawaiRepo.findByNamaContaining(query,pageable)) {
				PegawaiFormView pegawaiFormView = new PegawaiFormView();
				pegawaiFormView.setIdPegawai(p.getIdPegawai());
				pegawaiFormView.setNama(p.getNama());
				pegawaiFormView.setKota(p.getKota().getNama());
				pegawaiFormView.setTglLahir(new SimpleDateFormat("dd MMMM yyyy",new Locale("id")).format(p.getTanggalLahir()));
				pegawaiFormViews.add(pegawaiFormView);
			}
			//System.out.println(pegawaiFormViews.size());
			ListPegawaiView listPegawaiView = new ListPegawaiView();
			listPegawaiView.setPegawaiFormViewList(pegawaiFormViews);
			listPegawaiView.setItems(pegawaiRepo.findByNamaContaining(query,pageable).getTotalPages());
			//System.out.println(pegawaiRepo.findByNamaContaining(query,pageable).getTotalPages());
			listPegawaiView.setItemsOnPage(2);
			listPegawaiView.setQuery(query);
			listPegawaiView.setCurrentPage(sPage);
			model.addAttribute("listPegawaiView",listPegawaiView);
			return "pegawai";
		}

		@GetMapping("/addpegawai")
		public String addPegawai(Model model) {
			PegawaiFormView pegawaiFormView = new PegawaiFormView();

			List<KotaView> kotaViews = new ArrayList<>();
			for(Kota k : kotaRepo.findAll()) {
				KotaView kotaView = new KotaView();
				kotaView.setIdKota(k.getIdKota());
				kotaView.setNama(k.getNama());
				kotaView.setSelected(false);
				kotaViews.add(kotaView);
			}
			pegawaiFormView.setKotaViewModels(kotaViews);
			model.addAttribute("pegawaiFormView", pegawaiFormView);
			return "pegawai_form";
		}

		@GetMapping("/editpegawai")
		public String editPegawai(@RequestParam(name = "id") Integer idPegawai,Model model) {
			Pegawai p = pegawaiRepo.findOne(idPegawai);
			PegawaiFormView pegawaiFormView = new PegawaiFormView();
			pegawaiFormView.setNama(p.getNama());
			pegawaiFormView.setTglLahir(new SimpleDateFormat("dd MMMM yyyy",new Locale("id")).format(p.getTanggalLahir()));
			pegawaiFormView.setIdPegawai(p.getIdPegawai());
			List<KotaView> kotaViews = new ArrayList<>();
			for(Kota k : kotaRepo.findAll()) {
				KotaView kotaView = new KotaView();
				kotaView.setIdKota(k.getIdKota());
				kotaView.setNama(k.getNama());
				if(p.getKota().getIdKota()==k.getIdKota()) {
					kotaView.setSelected(true);
				}
				kotaViews.add(kotaView);
			}
			pegawaiFormView.setKotaViewModels(kotaViews);
			model.addAttribute("pegawaiFormView", pegawaiFormView);
			return "pegawai_form";
		}

	}

	@RestController
	public class MyrestController {

		@PostMapping("/simpanpegawai")
		public OperationResult simpanPegawai(@Valid PegawaiForm pegawaiInputModel, BindingResult bindingResult, Model
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
				pegawai.setTanggalLahir(new SimpleDateFormat("dd MMMM yyyy",new Locale("id")).parse(pegawaiInputModel.getTglLahir()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pegawai.setKota(kotaRepo.findOne(Integer.valueOf(pegawaiInputModel.getKota())));
			if(pegawaiInputModel.getIdPegawai()!=null) {
				pegawai.setIdPegawai(pegawaiInputModel.getIdPegawai());
			}
			pegawaiRepo.save(pegawai);
			operationResult.setSuccess(true);
			operationResult.setMessage(null);
			return operationResult;
		}
	}
}

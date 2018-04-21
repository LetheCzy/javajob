package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.SpittleNotFoundException;

@Controller
public class testController {
	
	
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String home(@RequestParam(value="max", defaultValue="10000") long max,
		    @RequestParam(value="count", defaultValue="20") int count) {
		return "index";//view name
	}
	
	/*@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
	public String spittle(
	    @PathVariable("spittleId") long spittleId, 
	    Model model) {
	  model.addAttribute(spittleId);
	  return "spittle";
	}*/
	
//	@RequestMapping(method=RequestMethod.POST)
//	public String saveSpittle(SpittleForm form, Model model) throws Exception {
//	  spittleRepository.save(new Spittle(null, form.getMessage(), new Date(), 
//	      form.getLongitude(), form.getLatitude()));
//	  return "redirect:/spittles";
//	}
	
	@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
	public String spittleException(@PathVariable("spittleId") long spittleId,
	        Model model) {
		
	    String spittle = String.valueOf(spittleId);
	    if (spittle == null || spittle == "0") {
	        throw new SpittleNotFoundException();
	    }
	    model.addAttribute(spittle);
	    return "spittleException";
	}
}

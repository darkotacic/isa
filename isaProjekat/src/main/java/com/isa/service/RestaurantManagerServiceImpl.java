package com.isa.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Product;
import com.isa.entity.RequestOffer;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;
import com.isa.entity.users.Bidder;
import com.isa.entity.users.Cook;
import com.isa.entity.users.RestaurantManager;
import com.isa.entity.users.UserRole;
import com.isa.entity.users.Waiter;
import com.isa.entity.users.Worker;
import com.isa.repository.BartenderRepository;
import com.isa.repository.BidderRepository;
import com.isa.repository.CookRepository;
import com.isa.repository.ProductRepository;
import com.isa.repository.RequestOfferRepository;
import com.isa.repository.RestaurantManagerRepository;
import com.isa.repository.RestaurantRepository;
import com.isa.repository.RestaurantTableRepository;
import com.isa.repository.SegmentRepository;
import com.isa.repository.UserRepository;
import com.isa.repository.WaiterRepository;
import com.isa.repository.WorkScheduleRepository;
import com.isa.repository.WorkerRepository;

@Service
@Transactional
public class RestaurantManagerServiceImpl implements RestaurantManagerService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SegmentRepository segmentRepository;

	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	@Autowired
	private CookRepository cookRepository;

	@Autowired
	private BartenderRepository bartenderRepository;

	@Autowired
	private WaiterRepository waiterRepository;

	@Autowired
	private WorkerRepository workerRepository;

	@Autowired
	private WorkScheduleRepository workScheduleRepository;

	@Autowired
	private BidderRepository bidderRepository;

	@Autowired
	private RestaurantManagerRepository restaurantManagerRepository;

	@Autowired
	private RequestOfferRepository requestOfferRepository;

	@Override
	public ResponseEntity<Restaurant> updateRestaurantProfile(Restaurant r) {
		Restaurant temp = this.restaurantRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setName(r.getName());
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(temp), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> defineRestaurantMenu(Long[] products, Long rest_id) {
		Restaurant t = this.restaurantRepository.findOne(rest_id);
		for (int i = 0; i < products.length; i++) {
			Product p = this.productRepository.findOne(products[i]);
			p.getRestaurants().add(t);
			t.getMenu().add(p);
		}
		this.restaurantRepository.save(t);
		return new ResponseEntity<String>("vidi bazu", HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Product> addProductToMenu(Product p, Long r_id) {
		Restaurant t = this.restaurantRepository.findOne(r_id);
		Set<Product> pro = new HashSet<Product>();
		if (t.getMenu() != null)
			pro = t.getMenu();
		Set<Restaurant> rest = new HashSet<Restaurant>();
		rest.add(t);
		p.setRestaurants(rest);
		pro.add(p);
		t.setMenu(pro);
		this.restaurantRepository.save(t);
		return new ResponseEntity<Product>(this.productRepository.save(p), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, Long r_id) {
		Restaurant r = this.restaurantRepository.findOne(r_id);
		s.setRestaurant(r);
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RestaurantTable> addRestaurantTableToSegment(RestaurantTable t, Long segment_id) {
		Segment s = this.segmentRepository.findOne(segment_id);
		t.setSegment(s);
		return new ResponseEntity<RestaurantTable>(this.restaurantTableRepository.save(t), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> removeSegment(Long id) {
		this.segmentRepository.delete(id);
		return new ResponseEntity<String>("Vidi bazu", HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<String> removeRestaurantTable(Long id) {
		this.restaurantTableRepository.delete(id);
		return new ResponseEntity<String>("Vidi bazu", HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<RestaurantTable> updateRestaurantTable(RestaurantTable t) {
		return new ResponseEntity<RestaurantTable>(this.restaurantTableRepository.save(t), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Segment> updateSegment(Segment s) {
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Cook> registerCook(Cook c, Long id) {
		c.setUserRole(UserRole.COOK);
		if (this.userRepository.findByEmail(c.getEmail()) != null || this.restaurantRepository.findOne(id) == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		c.setRestaurant(this.restaurantRepository.findOne(id));
		return new ResponseEntity<Cook>(this.cookRepository.save(c), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Bartender> registerBartender(Bartender w, Long id) {
		if (this.userRepository.findByEmail(w.getEmail()) != null || this.restaurantRepository.findOne(id) == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		w.setRestaurant(this.restaurantRepository.findOne(id));
		return new ResponseEntity<Bartender>(this.bartenderRepository.save(w), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Waiter> registerWaiter(Waiter w, Long id) {
		if (this.userRepository.findByEmail(w.getEmail()) != null || this.restaurantRepository.findOne(id) == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		w.setRestaurant(this.restaurantRepository.findOne(id));
		return new ResponseEntity<Waiter>(this.waiterRepository.save(w), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> removeWorker(Long id) {
		Worker w = this.workerRepository.findOne(id);
		if (w.getUserRole().equals(UserRole.COOK))
			this.cookRepository.delete(id);
		else if (w.getUserRole().equals(UserRole.WAITER))
			this.waiterRepository.delete(id);
		else
			this.bartenderRepository.delete(id);
		return new ResponseEntity<String>("Vidi bazu", HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<WorkSchedule> registerWorkSchedule(WorkSchedule w, Long worker_id, Long segment_id,
			Long replacement_id) {
		if (w.getEndTime() < 10.00 && w.getStartTime() > 18.00) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(w.getDate());
			cal.add(Calendar.DATE, 1);
			Date nextDay = cal.getTime();
			w.setDate(nextDay);
		}

		else if (w.getStartTime() > w.getEndTime())
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		Worker u = this.workerRepository.findOne(worker_id);
		if (u.getUserRole().equals(UserRole.WAITER)) {
			if (segment_id == 0 || this.segmentRepository.findByRestaurantAndId(u.getRestaurant(), segment_id) == null)
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			Segment s = this.segmentRepository.findOne(segment_id);
			System.out.println();
			if (replacement_id != 0) {
				Worker r = this.workerRepository.findOne(replacement_id);
				if (this.workScheduleRepository.findByWorkerAndDateAndStartTime(r, w.getDate(), w.getEndTime()) == null
						|| !u.getRestaurant().equals(r.getRestaurant()))
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				w.setReplacement(r);
			}
			w.setSegment(s);
		}
		w.setWorker(u);
		return new ResponseEntity<WorkSchedule>(this.workScheduleRepository.save(w), HttpStatus.CREATED);
	}

	@Override
	public String removeWorkSchedule(Long id) {
		this.workScheduleRepository.delete(id);
		return "Izbrisan";
	}

	@Override
	public ResponseEntity<Bidder> registerBidder(Bidder b) {
		if (this.userRepository.findByEmail(b.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<Bidder>(this.bidderRepository.save(b), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RequestOffer> registerRequestOffer(RequestOffer ro, Long r_id, Long[] pro_ids) {
		if (this.restaurantManagerRepository.findOne(r_id) == null || ro.getExpirationDate().before(ro.getStartDate()))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		for (int i = 0; i < pro_ids.length; i++)
			if (this.productRepository.findOne(pro_ids[i]) == null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			else {
				Product p = this.productRepository.findOne(pro_ids[i]);
				ro.getProducts().add(p);
				p.getRequestOffers().add(ro);
			}
		RestaurantManager rm = this.restaurantManagerRepository.findOne(r_id);
		ro.setRestaurantManager(rm);
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(ro), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RequestOffer> updateRequestOffer(RequestOffer ro, Long[] pro_add_ids, Long[] pro_rem_ids) {
		RequestOffer temp = this.requestOfferRepository.findOne(ro.getId());
		if (ro.getExpirationDate().before(ro.getStartDate()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		temp.setStartDate(ro.getStartDate());
		temp.setExpirationDate(ro.getExpirationDate());
		for (int i = 0; i < pro_rem_ids.length; i++) {
			Product p = this.productRepository.findOne(pro_rem_ids[i]);
			p.getRequestOffers().remove(ro);
			ro.getProducts().remove(p);
		}
		for (int i = 0; i < pro_add_ids.length; i++) {
			Product p = this.productRepository.findOne(pro_add_ids[i]);
			p.getRequestOffers().add(ro);
			ro.getProducts().add(p);
		}
		temp.setProducts(ro.getProducts());
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(temp), HttpStatus.ACCEPTED);

	}

	@Override
	public String removeRequestOffer(Long ro) {
		this.requestOfferRepository.delete(ro);
		return "nes";
	}

}

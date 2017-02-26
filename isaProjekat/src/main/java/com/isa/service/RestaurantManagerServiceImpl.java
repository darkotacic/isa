
package com.isa.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.BidderOffer;
import com.isa.entity.BidderOfferStatus;
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
import com.isa.repository.BidderOfferRepository;
import com.isa.repository.BidderRepository;
import com.isa.repository.CookRepository;
import com.isa.repository.OrderRepository;
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

	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

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

	@Autowired
	private BidderOfferRepository bidderOfferRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ResponseEntity<RestaurantManager> update(RestaurantManager sm) {
		RestaurantManager temp = this.restaurantManagerRepository.findOne(sm.getId());
		if (!temp.getEmail().equals(sm.getEmail()))
			if (this.userRepository.findByEmail(sm.getEmail()) != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfBirth(sm.getDateOfBirth());
		temp.setEmail(sm.getEmail());
		temp.setPassword(sm.getPassword());
		temp.setSurname(sm.getSurname());
		temp.setUserName(sm.getUserName());
		return new ResponseEntity<RestaurantManager>(this.restaurantManagerRepository.save(temp), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Restaurant> updateRestaurantProfile(Restaurant r) {
		Restaurant temp = this.restaurantRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setRestaurantName(r.getRestaurantName());
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> removeProductFromMenu(Long products, Long rest_id) {
		Restaurant t = this.restaurantRepository.findOne(rest_id);
		Product p = this.productRepository.findOne(products);
		p.getRestaurants().remove(t);
		t.getMenu().remove(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> addNewProductToMenu(Product p, Long r_id) {
		Restaurant t = this.restaurantRepository.findOne(r_id);
		t.getMenu().add(p);
		p.getRestaurants().add(t);
		return new ResponseEntity<Product>(this.productRepository.save(p), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> addExistingProductToMenu(Long p_id, Long r_id) {
		Restaurant t = this.restaurantRepository.findOne(r_id);
		Product p = this.productRepository.findOne(p_id);
		t.getMenu().add(p);
		p.getRestaurants().add(t);
		return new ResponseEntity<Product>(this.productRepository.save(p), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, Long r_id) {
		Restaurant r = this.restaurantRepository.findOne(r_id);
		s.setRestaurant(r);
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RestaurantTable> addRestaurantTableToSegment(RestaurantTable t, Long segment_id) {
		Segment s = this.segmentRepository.findOne(segment_id);
		t.setSegment(s);
		return new ResponseEntity<RestaurantTable>(this.restaurantTableRepository.save(t), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> removeSegment(Long id) {
		this.segmentRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RestaurantTable> removeRestaurantTable(Long id) {
		if (this.restaurantTableRepository.findOne(id).isFree())
			this.restaurantTableRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RestaurantTable> updateRestaurantTable(RestaurantTable t, Long id) {
		t.setSegment(this.segmentRepository.findOne(id));
		return new ResponseEntity<RestaurantTable>(this.restaurantTableRepository.save(t), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> updateSegment(Segment s) {
		Segment temp = this.segmentRepository.findOne(s.getId());
		s.setRestaurant(temp.getRestaurant());
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Cook> registerCook(Cook c, Long id) {
		if (this.userRepository.findByEmail(c.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		c.setUserRole(UserRole.COOK);
		c.setRestaurant(this.restaurantRepository.findOne(id));
		return new ResponseEntity<Cook>(this.cookRepository.save(c), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Bartender> registerBartender(Bartender w, Long id) {
		if (this.userRepository.findByEmail(w.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		w.setRestaurant(this.restaurantRepository.findOne(id));
		return new ResponseEntity<Bartender>(this.bartenderRepository.save(w), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Waiter> registerWaiter(Waiter w, Long id) {
		if (this.userRepository.findByEmail(w.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		w.setRestaurant(this.restaurantRepository.findOne(id));
		return new ResponseEntity<Waiter>(this.waiterRepository.save(w), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Worker> removeWorker(Long id) {
		this.workerRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WorkSchedule> registerWorkSchedule(WorkSchedule w, Long worker_id, Long segment_id) {
		String now = formatter.format(new Date());
		String date = formatter.format(w.getDate());
		if (!now.equals(date))
			if (w.getDate().before(new Date()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (w.isTwoDays()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(w.getDate());
			cal.add(Calendar.DATE, 1);
			Date nextDay = cal.getTime();
			w.setSecondDate(nextDay);
		} else if (w.getStartTime() > w.getEndTime())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Worker u = this.workerRepository.findOne(worker_id);
		if (u.getUserRole().equals(UserRole.WAITER)) {
			if (segment_id == 0)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			Segment s = this.segmentRepository.findOne(segment_id);
			w.setSegment(s);
		}
		w.setWorker(u);
		return new ResponseEntity<WorkSchedule>(this.workScheduleRepository.save(w), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WorkSchedule> updateWorkSchedule(WorkSchedule w) {
		String now = formatter.format(new Date());
		String date = formatter.format(w.getDate());
		if (!now.equals(date))
			if (w.getDate().before(new Date()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		WorkSchedule temp = this.workScheduleRepository.findOne(w.getId());
		temp.setDate(w.getDate());
		if (w.isTwoDays() && !temp.isTwoDays()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(w.getDate());
			cal.add(Calendar.DATE, 1);
			Date nextDay = cal.getTime();
			temp.setSecondDate(nextDay);
			temp.setTwoDays(true);
		} else if (!w.isTwoDays() && temp.isTwoDays()) {
			temp.setTwoDays(false);
			temp.setSecondDate(null);
		}
		temp.setEndTime(w.getEndTime());
		temp.setStartTime(w.getStartTime());
		return new ResponseEntity<WorkSchedule>(this.workScheduleRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WorkSchedule> updateWorkScheduleSetReplacement(Long s, Long w) {
		WorkSchedule ws = this.workScheduleRepository.findOne(w);
		WorkSchedule pr = this.workScheduleRepository.findOne(s);
		ws.setReplacement(this.waiterRepository.findOne(pr.getWorker().getId()));
		return new ResponseEntity<WorkSchedule>(this.workScheduleRepository.save(ws), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WorkSchedule> updateWorkScheduleSetSegment(Long s, Long w) {
		WorkSchedule ws = this.workScheduleRepository.findOne(w);
		ws.setSegment(this.segmentRepository.findOne(s));
		return new ResponseEntity<WorkSchedule>(this.workScheduleRepository.save(ws), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WorkSchedule> updateWorkScheduleSetWorker(Long s, Long w) {
		WorkSchedule ws = this.workScheduleRepository.findOne(w);
		ws.setWorker(this.workerRepository.findOne(s));
		return new ResponseEntity<WorkSchedule>(this.workScheduleRepository.save(ws), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WorkSchedule> removeWorkSchedule(Long id) {
		this.workScheduleRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Bidder> registerBidder(Bidder b) {
		if (this.userRepository.findByEmail(b.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<Bidder>(this.bidderRepository.save(b), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestOffer> registerRequestOffer(RequestOffer ro, Long r_id) {
		String now = formatter.format(new Date());
		String date = formatter.format(ro.getStartDate());
		if (ro.getExpirationDate().before(ro.getStartDate()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (!date.equals(now)) {
			ro.setStatus(false);
			if (ro.getStartDate().before(new Date()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		RestaurantManager rm = this.restaurantManagerRepository.findOne(r_id);
		ro.setRestaurantManager(rm);
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(ro), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> addProductToRequestOffer(Long p_id, Long r_id) {
		RequestOffer t = this.requestOfferRepository.findOne(r_id);
		Product p = this.productRepository.findOne(p_id);
		t.getProducts().add(p);
		p.getRequestOffers().add(t);
		return new ResponseEntity<Product>(this.productRepository.save(p), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> removeProductFromRequestOffer(Long p_id, Long r_id) {
		RequestOffer t = this.requestOfferRepository.findOne(r_id);
		Product p = this.productRepository.findOne(p_id);
		t.getProducts().remove(p);
		p.getRequestOffers().remove(t);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestOffer> updateRequestOffer(RequestOffer ro) {
		String now = formatter.format(new Date());
		String date = formatter.format(ro.getStartDate());
		RequestOffer temp = this.requestOfferRepository.findOne(ro.getId());
		if (ro.getExpirationDate().before(ro.getStartDate()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (!date.equals(now))
			if (ro.getStartDate().before(new Date()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (now.equals(date)) {
			ro.setStatus(false);
		}
		temp.setStartDate(ro.getStartDate());
		temp.setExpirationDate(ro.getExpirationDate());
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(temp), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<RequestOffer> removeRequestOffer(Long ro) {
		this.requestOfferRepository.delete(ro);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Worker>> getAllWorkersForRestaurant(Long id) {
		return new ResponseEntity<List<Worker>>(
				(List<Worker>) this.workerRepository.findByRestaurant(this.restaurantRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<WorkSchedule>> getAllWorkSchedulesForRestaurant(Long id) {
		return new ResponseEntity<List<WorkSchedule>>(
				this.workScheduleRepository.getWorkScheduleForRestaurant(this.restaurantRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<WorkSchedule>> getAllWorkSchedulesForWorker(Long id) {
		return new ResponseEntity<List<WorkSchedule>>(
				this.workScheduleRepository.findByWorker(this.workerRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RequestOffer>> getAllRequestOffersForManager(Long id) {
		return new ResponseEntity<List<RequestOffer>>(
				this.requestOfferRepository.findByRestaurantManager(this.restaurantManagerRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BidderOffer>> getAllBidderOffersForManagerOffers(Long id) {
		return new ResponseEntity<List<BidderOffer>>(
				this.bidderOfferRepository.getBidderOffersForManager(this.restaurantManagerRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BidderOffer>> getAllBidderOffersForRequestOffer(Long id) {
		return new ResponseEntity<List<BidderOffer>>(
				this.bidderOfferRepository.findByRequestOffer(this.requestOfferRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Segment>> getAllSegmentsForRestaurant(Long id) {
		return new ResponseEntity<List<Segment>>(
				this.segmentRepository.findByRestaurant(this.restaurantRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RestaurantTable>> getAllTablesForRestaurant(Long id) {
		return new ResponseEntity<List<RestaurantTable>>(
				this.restaurantTableRepository.getTablesForRestaurant(this.restaurantRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RestaurantTable>> getAllTablesForSegment(Long id) {
		return new ResponseEntity<List<RestaurantTable>>((List<RestaurantTable>) this.restaurantTableRepository
				.findBySegment(this.segmentRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Product>> getAllProductsForRestaurant(Long id) {
		return new ResponseEntity<List<Product>>(this.productRepository.getProductsForRestaurant(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestOffer> acceptBidderOffer(Long r_id, Long q_id) {
		RequestOffer ro = this.requestOfferRepository.findOne(q_id);
		ro.setStatus(false);
		List<BidderOffer> ibo = this.bidderOfferRepository.findByRequestOffer(ro);
		for (int i = 0; i < ibo.size(); i++) {
			if (!ibo.get(i).getId().equals(r_id))
				ibo.get(i).setOfferStatus(BidderOfferStatus.DECLINED);
			else
				ibo.get(i).setOfferStatus(BidderOfferStatus.ACCEPTED);
		}
		this.bidderOfferRepository.save(ibo);
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(ro), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<WorkSchedule>> getPossableReplacements(Long id) {
		WorkSchedule w = this.workScheduleRepository.findOne(id);
		if (!w.isTwoDays())
			return new ResponseEntity<List<WorkSchedule>>(this.workScheduleRepository
					.getReplacements(w.getWorker().getRestaurant(), w.getEndTime(), w.getDate()), HttpStatus.OK);
		else
			return new ResponseEntity<List<WorkSchedule>>(this.workScheduleRepository
					.getReplacements(w.getWorker().getRestaurant(), w.getEndTime(), w.getSecondDate()), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<Product>> getAllProductsForRequestOffer(Long id) {
		return new ResponseEntity<List<Product>>(this.productRepository.getProductsForRequestOffer(id), HttpStatus.OK);
	}

	@Override
	public double gradeForOrder(Long id, Long res_id) {
		if (this.productRepository.seeIfBelongsToRestaurant(id, res_id) == null
				|| this.orderRepository.getGradeForOrder(id, res_id) == null)
			return -1;
		return this.orderRepository.getGradeForOrder(id, res_id);
	}

	@Override
	public double gradeForWorker(Long id) {
		if (this.orderRepository.getGradeForWorker(id) == null)
			return -1;
		return this.orderRepository.getGradeForWorker(id);
	}

	@Override
	public double gradeForRestaurant(Long id) {
		if (this.restaurantRepository.getGradeForRestaurant(id) == null)
			return -1;
		return this.restaurantRepository.getGradeForRestaurant(id);
	}

	@Override
	public double restaurantEarnings(Long id, Date startDate, Date endDate) {
		if (this.waiterRepository.getEarningsForRestaurant(this.restaurantRepository.findOne(id), startDate,
				endDate) == null)
			return -1;
		return this.waiterRepository.getEarningsForRestaurant(this.restaurantRepository.findOne(id), startDate,
				endDate);
	}

	@Override
	public double waiterEarinings(Long id) {
		if (this.waiterRepository.getEarningsForWaiter(id) == null)
			return -1;
		return this.waiterRepository.getEarningsForWaiter(id);
	}

	@Override
	public double checkIfRequestOfferExpired() {

		List<RequestOffer> of = (List<RequestOffer>) this.requestOfferRepository.findAll();
		for (int i = 0; i < of.size(); i++) {
			String now = formatter.format(new Date());
			String date = formatter.format(of.get(i).getStartDate());
			if (of.get(i).getExpirationDate().before(new Date())) {
				of.get(i).setStatus(false);
				List<BidderOffer> it = this.bidderOfferRepository.findByRequestOffer(of.get(i));
				for (int j = 0; j < it.size(); j++) {
					it.get(j).setOfferStatus(BidderOfferStatus.DECLINED);
				}
				this.bidderOfferRepository.save(it);
				this.requestOfferRepository.save(of.iterator().next());
			} else if (now.equals(date)) {
				of.get(i).setStatus(true);
				this.requestOfferRepository.save(of.get(i));
			}
		}
		return 0;
	}

	@Override
	public double checkIfWorkScheduleIsDone() {
		List<WorkSchedule> of = (List<WorkSchedule>) this.workScheduleRepository.findAll();
		for (int i = 0; i < of.size(); i++) {
			String now = formatter.format(new Date());
			String date = formatter.format(of.get(i).getDate());
			if (!date.equals(now))
				if (of.get(i).getDate().before(new Date())) {
					of.get(i).setDone(true);
					this.workScheduleRepository.save(of.iterator().next());
				}
		}
		return 0;
	}

	@Override
	public ResponseEntity<Restaurant> getRestaurantForManager(Long id) {
		return new ResponseEntity<Restaurant>(
				this.restaurantRepository.getByManager(this.restaurantManagerRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<List<Product>>((List<Product>) this.productRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public double checkIfSegmentCanBeDeleted(Long id) {
		return this.restaurantTableRepository.seeIfCanDeleteSegment(id).size();
	}

}

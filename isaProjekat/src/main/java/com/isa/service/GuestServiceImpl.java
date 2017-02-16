package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.users.Friend;
import com.isa.entity.users.Guest;
import com.isa.repository.GuestRepository;


@Service
@Transactional
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	private GuestRepository guestRepository;


	@Override
	public ResponseEntity<List<Guest>> getFriendsForGuest(Long id) {
		if(id != null){
			Guest guest = guestRepository.findOne(id);
			List<Guest>friends=this.guestRepository.getFriendsForSender(guest);
			List<Guest>friends1=this.guestRepository.getFriendsForReciever(guest);
			
			for(Guest g : friends1){
				friends.add(g);
			}
			
			return new ResponseEntity<List<Guest>>(friends,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Friend> sendRequest(Long sender_id, Long reciever_id) {
		
		if(sender_id != null && reciever_id != null && sender_id != reciever_id){
		
			Guest sender = guestRepository.findOne(sender_id);
			Guest reciever = guestRepository.findOne(reciever_id);
			
			
			Friend f = new Friend();
			f.setReciever(reciever);
			f.setSender(sender);
			f.setStatus(false);
			
			sender.getSent().add(f);
			this.guestRepository.save(sender);
			return new ResponseEntity<Friend>(f,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
		
	}

}

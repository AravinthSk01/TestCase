package com.rvz.transfer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rvz.transfer.entity.Transaction;
import com.rvz.transfer.entity.User;
import com.rvz.transfer.repository.TransactionRepository;
import com.rvz.transfer.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	UserRepository userrepository;
	
	@Mock
	TransactionRepository transactionrepository;
	
	@InjectMocks
	UserService userservice;
	
	User u = new User();
	
	@Test
	// Adding new user
	public void test1() {
		
		u.setAmount(1000);
		u.setUsername("Tamil");
		u.setAccountnumber(256789);
		
		assertEquals("ok", userservice.addAmount(u));
	}
	
	
	
	// View single user profile by account number
	
	private void assertArrayEquals(String string2, String string) {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void test2() {		
//		assertArrayEquals(u,userservice.getUserTransactions(256789));
//		
	}
	
		@Test
		public void test3() {			
			assertArrayEquals(u, userservice.viewAmount());
		}
		

		private void assertArrayEquals(User u2, List<User> viewAmount) {
			// TODO Auto-generated method stub
			
		}
	
	
	@Test
	void test4() {
//		assertArrayEquals(u, userservice.viewProfile(256789));
		
		User u2 = new User();
		u2.setAmount(10000);
		u2.setUsername("Kavi");
		u2.setAccountnumber(45657);
		
		assertArrayEquals("Sender not found", userservice.transferAmount(45657, 256789 , 400,"Tamil"));
		
	}

	    @Test
	    void test5() {
	        List<User> users = List.of(new User(), new User());
	        when(userrepository.findAll()).thenReturn(users);

	        List<User> out = userservice.viewAmount();

	        assertThat(out).hasSize(2);
	        verify(userrepository).findAll();
	    }

	    @Test
	    void test6() {
	        User u = new User();
	        u.setAccountnumber(2001);
	        when(userrepository.findByAccountnumber(2001)).thenReturn(Optional.of(u));

	        User out = userservice.viewProfile(2001);

	        assertThat(out).isSameAs(u);
	        verify(userrepository).findByAccountnumber(2001);
	    }

	    @Test
	    void test7() {
	        User sender = new User();
	        sender.setAccountnumber(1111);
	        sender.setAmount(500);

	        User receiver = new User();
	        receiver.setAccountnumber(2222);
	        receiver.setAmount(200);

	        when(userrepository.findByAccountnumber(1111)).thenReturn(Optional.of(sender));
	        when(userrepository.findByAccountnumber(2222)).thenReturn(Optional.of(receiver));
	        when(userrepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
	        when(transactionrepository.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

	        String res = userservice.transferAmount(1111, 2222, 150, null);

	        assertThat(res).isEqualTo("Transfer successful!");
	        assertThat(sender.getAmount()).isEqualTo(350);
	        assertThat(receiver.getAmount()).isEqualTo(350);

	        // Verify save order: sender -> receiver
	        InOrder inOrder = Mockito.inOrder(userrepository, transactionrepository);
	        inOrder.verify(userrepository).save(sender);
	        inOrder.verify(transactionrepository).save(any(Transaction.class)); // DEBIT
	        inOrder.verify(userrepository).save(receiver);
	        inOrder.verify(transactionrepository).save(any(Transaction.class)); // CREDIT

	        verify(transactionrepository, times(2)).save(any(Transaction.class));
	    }

	    
	    @Test
	    void testcase1() {
	    	User s = new User();
	    	s.setAccountnumber(12233);
	    	s.setUsername("Sivakumar");
	    	s.setAmount(3000);
	    	
	    	assertEquals("ok", userservice.addAmount(s));
	    }
	}


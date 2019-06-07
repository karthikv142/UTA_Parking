package uta_parking.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)
public class PaymentTest {
	Payment paymenttest;
	@Before
	public void setUp() throws Exception {
		paymenttest = new Payment();
	}

	@Test
	@FileParameters("src/BackEndTests/PaymentTestCase.csv")
	public void test(int testcaseno, String credit_card_number, String credit_card_type ,String errors) {
		PaymentErrorMsgs paymenterror1 = new PaymentErrorMsgs();
		String paymenterror; 
		paymenttest.setCredit_card_number(credit_card_number);
		paymenttest.setCredit_card_type(credit_card_type);
		paymenterror = paymenttest.validatePayment(paymenttest.getCredit_card_number(), paymenttest.getCredit_card_type());
		assertEquals(errors,paymenterror);
	
	}

}

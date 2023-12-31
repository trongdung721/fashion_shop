//package com.ecommerce.controller.shop.order;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ecommerce.service.OrderService;
//
//@WebServlet(name = "PlaceOrderServlet", value = "/place_order")
//public class PlaceOrderServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		OrderService orderService = new OrderService(request, response);
//		orderService.placeOrder();
//	}
//
//}
package com.ecommerce.controller.shop.order;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.model.entity.Customer;
import com.ecommerce.service.OrderService;

@WebServlet(name = "PlaceOrderServlet", value = "/place_order")
public class PlaceOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = new OrderService(request, response);
		orderService.placeOrder();

		// Gửi email sau khi đặt hàng
		sendOrderConfirmationEmail(request);
	}

	private void sendOrderConfirmationEmail(HttpServletRequest request) {
//		HttpSession session1 = request.getSession();
//		Customer customer = (Customer) session1.getAttribute("loggedCustomer");
		final String username = "21110294@student.hcmute.edu.vn"; // Thay thế bằng email của bạn
		final String password = "Gohuynh232003@"; // Thay thế bằng mật khẩu của bạn

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		String emailTo = "vohuutai2369@gmail.com"; // Thay thế bằng địa chỉ email của người nhận
//		String emailTo = request.getParameter(customer.getEmail());
		String emailSubject = "Order Confirmation";
		String emailContent = "Thank you for your order. It has been successfully placed.";
		System.out.println("Email la:"+ emailTo);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(emailSubject);
			message.setText(emailContent);
			Transport.send(message);
			System.out.println("Order confirmation email sent successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			// Xử lý lỗi khi gửi email
		}
	}
}


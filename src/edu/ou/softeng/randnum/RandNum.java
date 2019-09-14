package edu.ou.softeng.randnum;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.PriorityQueue;

/**
 * Servlet implementation class RandNum
 */
@WebServlet("/")
public class RandNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandNum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> numbers = new PriorityQueue<Integer>(); //Keep track of 750 numbers
		Integer num = new Integer(new Random().nextInt(99999) + 1); //Create new random number. Make sure its between 1 and 1 million
		if(numbers.contains(num)) //If the number is already in the queue 
		{
			while(numbers.contains(num)) //As long as we keep generating numbers already in the queue
			{
				num = new Integer(new Random().nextInt(1000000)); //Generate a new number
			}
		}
		response.getWriter().append(num.toString()); //Send out our new number
		numbers.add(num); //Add new number to queue 
		if(numbers.size() >= 750) //If we get above 750 numbers
			numbers.poll(); //Remove a number from queue
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

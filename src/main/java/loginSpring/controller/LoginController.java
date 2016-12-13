package loginSpring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loginSpring.common.LoginResponse;
import loginSpring.common.ResultCode;
import loginSpring.po.User;
import loginSpring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lenovo on 11/3/2016.
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * user log in
     * @param request the received http request, will save the succfully logged in user name in session
     * @param user the user to be verified
     * @return go to product list view if logged in successfully, otherwise stay in index view
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, @ModelAttribute("user")User user)
    {
        ModelAndView model= null;
        try
        {
            //String username=request.getParameter("userName");
            //String pass=request.getParameter("userPassword");
            //System.out.println("username is: "+ username);
            //System.out.println("pass is: " + pass);
            System.out.println("The user info is: " + user.getUserName() + user.getUserPassword());
            LoginResponse loginResponse = userService.verifyUser(user.getUserName(), user.getUserPassword());
            if(loginResponse.getCode().equals(ResultCode.PASS))
            {
                System.out.println("User Login Successful");
                request.setAttribute("loggedInUser", user.getUserName());
                //model = new ModelAndView("welcome");
//                model = new ModelAndView("productlist");
                model = new ModelAndView("productlistAGJS");
                HttpSession session = request.getSession(true);
                session.setAttribute("userName", user.getUserName());
                System.out.println(session.getAttribute("userName"));
            }
            else
            {
                //model = new ModelAndView("login");
                model = new ModelAndView("index");
                model.addObject("user", user);
                request.setAttribute("message", "Login failed!");
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return model;
    }
}

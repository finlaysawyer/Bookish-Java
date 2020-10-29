package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Loan;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.LoansPageModel;
import org.softwire.training.bookish.models.page.MembersPageModel;
import org.softwire.training.bookish.services.LoansService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/members")
public class LoanController {

    private final LoansService loansService;

    @Autowired
    public LoanController(LoansService loansService) {
        this.loansService = loansService;
    }

    @RequestMapping("")
    ModelAndView loans(@RequestParam(value = "search", required = false) String search) {
        LoansPageModel loansPageModel = new LoansPageModel();
        List<Loan> allLoans;
        if (search == null) {
            allLoans = loansService.getAllLoans();
        } else {
            allLoans = loansService.getLoansSearch(search);
        }
        loansPageModel.setLoans(allLoans);
        return new ModelAndView("loans", "model", loansPageModel);
    }

    @RequestMapping("/add-loan")
    ModelAndView addLoan() {
        return new ModelAndView("add-loan");
    }

    @RequestMapping("/add-loan/action")
    RedirectView addLoanAction(@ModelAttribute Loan loan) {

        loansService.addLoan(loan);

        return new RedirectView("/loans");
    }

    @RequestMapping("/delete-loan")
    RedirectView deleteLoan(@RequestParam int loan_id) {

        loansService.deleteLoan(loan_id);

        return new RedirectView("/loans");
    }
}

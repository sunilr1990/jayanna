/*
 *
 */
package com.assignment.question.paper.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.question.papaer.generator.service.QuestionPaperGeneratorService;
import com.assignment.question.paper.generator.exception.QuestionLibraryException;
import com.assignment.question.paper.generator.exception.QuestionPaperValidationException;
import com.assignment.question.paper.generator.model.ApiError;
import com.assignment.question.paper.generator.model.QuestionPaper;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jayavardhan.hegde
 *
 */

@RestController
@RequestMapping(value = "/questionpaper", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class QuestionGeneratorController {

    @Autowired
    private QuestionPaperGeneratorService questionPaperGeneratorService;

    @Validated
    @GetMapping("/generate")
    public ResponseEntity<Object> generatePaper(@Valid @RequestParam(required = true, value = "totalMarks") @Min(5) @Max(100) Integer totalMarks,
            @RequestParam(required = true, value = "stratergy") String stratergy,
            @RequestParam(required = true, value = "distributions") Integer[] distributions) {
        try {
            QuestionPaper questionPaper = questionPaperGeneratorService.generateQuestionPapaer(totalMarks, stratergy,
                    distributions);
            return new ResponseEntity<Object>(questionPaper, HttpStatus.OK);
        } catch (QuestionPaperValidationException qe) {
            ApiError apiError = new ApiError();
            apiError.setErrorCode(qe.getErrorCode());
            apiError.setErrorMessage(qe.getErrorMessage());
            return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
        } catch (QuestionLibraryException e) {
            ApiError apiError = new ApiError();
            apiError.setErrorCode(e.getErrorCode());
            apiError.setErrorMessage(e.getErrorMessage());
            return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

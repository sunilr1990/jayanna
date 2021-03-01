/*
 *
 */
package com.assignment.question.paper.generator.exception;

/**
 * @author jayavardhan.hegde
 *
 */
public class QuestionPaperValidationException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public QuestionPaperValidationException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}

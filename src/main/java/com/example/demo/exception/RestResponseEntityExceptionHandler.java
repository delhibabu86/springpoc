package com.example.demo.exception;

import org.hibernate.HibernateException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.*;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  /*  @Autowired
    private MessageSource validationMessage;*/

    public RestResponseEntityExceptionHandler() {
        super();
    }

    // Client firing request with wrong request method.405 method not allowed
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    // Client firing request with no content type or wrong content type.415 Unsupported media type
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    // Client firing request with wrong ACCEPT header.i.e we ask for xml if server configured for json .406 NOT_ACCEPTABLE
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    // todo
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    // request missing mandatory request param in the url. 400 BAD Request
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    // todo
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }
    // todo
    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

    // This gets called when request validation fails.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            System.out.println("Field :"+fieldName+"errorMessage :"+errorMessage);
            errors.put(fieldName, errorMessage);
        });
        /*BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();*/
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleBindException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }


    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request) {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> set = ((ConstraintViolationException) cause).getConstraintViolations();
            for (Iterator<ConstraintViolation<?>> iterator = set.iterator(); iterator.hasNext(); ) {
                ConstraintViolation<?> next = iterator.next();
                return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
            }
        }
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        String message = "Data Integrity Violation";
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler({MaxUploadSizeExceededException.class})
    protected ResponseEntity<Object> handleFileMaxupload(final MaxUploadSizeExceededException ex, final WebRequest request) {
        // log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleValidation(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class , NoSuchElementException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({BusinessValidationException.class})
    public ResponseEntity<Object> handleBadRequest(BusinessValidationException ex, WebRequest request) {
      return  handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({HibernateException.class})
    public ResponseEntity<Object> handleBadRequest(HibernateException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}

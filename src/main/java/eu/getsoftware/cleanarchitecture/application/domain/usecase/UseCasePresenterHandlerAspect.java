package eu.getsoftware.cleanarchitecture.application.domain.usecase;

import eu.getsoftware.cleanarchitecture.application.domain.model.domain.BusinessException;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@RequiredArgsConstructor
public class UseCasePresenterHandlerAspect {

    private final IUserResponseDTOPortPresenter responseDTOPresenter;

    UseCasePresenterHandlerAspect(IUserResponseDTOPortPresenter iUserDomainResponseDTO){
        this.responseDTOPresenter = iUserDomainResponseDTO;
    }
    
//    @Around("execution(* eu.getsoftware.cleanarchitecture.*.usecase.*.*(..))")
//    public Object handleUseCaseExceptions(ProceedingJoinPoint joinPoint) {
//        try {
//            return joinPoint.proceed();
//        } catch (Exception e) {
//            return responseDTOPresenter.prepareFailView(e.getMessage());
//        } catch (Throwable e) {
//            return responseDTOPresenter.prepareFailView(e.getMessage());
//        }
//    }

    @AfterReturning(pointcut = "execution(* eu.getsoftware.cleanarchitecture..usecase.impl.*.*(..))", returning = "result")
    public Object handleUseCaseResponse(Object result) {
        try {
            if(result instanceof IUserDomainResponseDTO)
                return responseDTOPresenter.prepareSuccessView((IUserDomainResponseDTO) result);// Обработка успеха
            return result;
        } catch (Exception ex) {
            return responseDTOPresenter.prepareFailView(ex); // Обработка ошибок
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @AfterThrowing(pointcut = "execution(* eu.getsoftware.cleanarchitecture..usecase.impl.*.*(..))", throwing = "exception")
    public Object handleFailure(Exception exception) {

        if (exception instanceof BusinessException || exception instanceof IllegalArgumentException) {
            return responseDTOPresenter.prepareFailView(exception);
        }

        return responseDTOPresenter.prepareFailView(new RuntimeException("Unexpected failure"));
    }

    
}
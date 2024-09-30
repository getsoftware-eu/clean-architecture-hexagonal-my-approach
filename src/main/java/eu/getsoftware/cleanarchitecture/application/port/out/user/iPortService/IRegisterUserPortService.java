package eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService;

public interface IRegisterUserPortService {
    
    public Boolean existsByName(String name);

}

//package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;
//
//import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity;
//import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity;
//
///**
// * Eugen:
// * Layer of "business rules" + Entity (data):
// * 
// * Package PRIVACY: should be not public OUTSIDE PACKAGE
// * 
// */
//class UserEntityImpl implements IUserEntity, IUserEntity
//{
//    String name;
//    String password;
//    UserAddresValueObject address;
//    UserAddresValueObject userAddresValueObject;
//
//    UserEntityImpl(String name, String password) {
//        this.name = name;
//        this.password = password;
//    }
//
//    UserEntityImpl() {}
//    
//    /**
//     * Eugen:
//     * Слой of "business rules":
//     *
//     * The user's password must have more than five characters
//     * @return
//     */
//    @Override
//    public boolean passwordIsValid() {
//        return password != null && password.length() > 5;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }    
//    
//    @Override
//    public UserAddresValueObject getAddress() {
//        return address;
//    }
//}

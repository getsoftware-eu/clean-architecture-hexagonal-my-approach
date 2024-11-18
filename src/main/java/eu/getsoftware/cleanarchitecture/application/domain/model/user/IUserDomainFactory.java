//package eu.getsoftware.cleanarchitecture.application.domain.model.user;
//
//import eu.getsoftware.cleanarchitecture.application.domain.model.AddressValueObject;
//
///**
// * Eugen: 
// * We created a user factory method because of two reasons:
// *
// * To stock to the stable abstractions principle 
// * and to ISOLATE the user creation.
// */
//public interface IUserDomainFactory<T extends UserRootDomainEntity/*, Z extends IUserDTO*/>
//{
//    T create(String name, String email, String password);
//    T create(String name, String email, String password, AddressValueObject address);
//
////    <B extends Object, I extends EntityIdentifier> B getEntityBuilder(I domainEntityId);
//}

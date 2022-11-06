package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserEntity;

/**
 * Eugen:
 * Слой of "business rules" + Entity (data):
 * 
 * Package PRIVACY: не должен быть public OUTSIDE PACKAGE
 * 
 * Eugen: maybe nur ValueObject is record
 */
class CommonUserEntityEntity implements UserEntity
{
    String name;
    String password;

    CommonUserEntityEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    CommonUserEntityEntity() {
    }
    
    /**
     * Eugen:
     * Слой of "business rules":
     *
     * The user's password must have more than five characters
     * @return
     */
    @Override
    public boolean passwordIsValid() {
        return password != null && password.length() > 5;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

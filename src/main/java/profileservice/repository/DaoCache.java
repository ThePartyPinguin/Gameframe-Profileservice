package profileservice.repository;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public abstract class DaoCache<J extends JpaRepository<T, I>, T, I>{

    protected Map<I, T> localCache;

    protected J jpaRepository;

    public DaoCache() {
        this.localCache = new HashMap<>();
    }

    public abstract void init(J jpaRepository);

    public T save (T o) {

        T returnValue = this.jpaRepository.save(o);

        Reflections r = new Reflections(o.getClass().getPackage().getName(), new FieldAnnotationsScanner());
        Field field = r.getFieldsAnnotatedWith(Id.class).iterator().next();

        try {
            I id = (I)field.get(o);

            if(this.localCache.containsKey(id)){
                
                this.localCache.replace(id, returnValue);
            }
            else{
                this.localCache.put(id, returnValue);
            }

            return returnValue;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void saveToCache(I id, T object){
        if(this.localCache.containsKey(id)){
            this.localCache.replace(id, object);
        }
        else{
            this.localCache.put(id, object);
        }
    }
}

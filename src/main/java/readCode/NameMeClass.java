package readCode;

import java.util.ArrayList;

interface ObjectFactory<T>{
    T create();
}

public class NameMeClass<T>
{
    private final ArrayList<T> nameMe = new ArrayList<>();
    private final ObjectFactory<T> factory;
    private final Object lock = new Object();

    public NameMeClass(ObjectFactory<T> _factory)
    {
        if (_factory == null)
            throw new IllegalArgumentException("_factory");

        factory = _factory;
    }

    public T allocate()
    {
        synchronized (lock)
        {
            if (nameMe.isEmpty())
                return factory.create();

            return nameMe.remove(0);
        }
    }

    public void free (T object)
    {
        if (object == null)
            throw new IllegalArgumentException("object");

        synchronized(lock)
        {
            nameMe.add(object);
        }
    }
}

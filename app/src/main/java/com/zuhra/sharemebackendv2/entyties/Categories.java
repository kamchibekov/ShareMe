package com.zuhra.sharemebackendv2.entyties;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Categories
{
  private String name;
  private java.util.Date updated;
  private java.util.Date created;
  private String objectId;
  private String ownerId;
  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public Categories save()
  {
    return Backendless.Data.of( Categories.class ).save( this );
  }

  public Future<Categories> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Categories> future = new Future<Categories>();
      Backendless.Data.of( Categories.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Categories> callback )
  {
    Backendless.Data.of( Categories.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Categories.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( Categories.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Categories.class ).remove( this, callback );
  }

  public static Categories findById( String id )
  {
    return Backendless.Data.of( Categories.class ).findById( id );
  }

  public static Future<Categories> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Categories> future = new Future<Categories>();
      Backendless.Data.of( Categories.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Categories> callback )
  {
    Backendless.Data.of( Categories.class ).findById( id, callback );
  }

  public static Categories findFirst()
  {
    return Backendless.Data.of( Categories.class ).findFirst();
  }

  public static Future<Categories> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Categories> future = new Future<Categories>();
      Backendless.Data.of( Categories.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Categories> callback )
  {
    Backendless.Data.of( Categories.class ).findFirst( callback );
  }

  public static Categories findLast()
  {
    return Backendless.Data.of( Categories.class ).findLast();
  }

  public static Future<Categories> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Categories> future = new Future<Categories>();
      Backendless.Data.of( Categories.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Categories> callback )
  {
    Backendless.Data.of( Categories.class ).findLast( callback );
  }

  public static BackendlessCollection<Categories> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Categories.class ).find( query );
  }

  public static Future<BackendlessCollection<Categories>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Categories>> future = new Future<BackendlessCollection<Categories>>();
      Backendless.Data.of( Categories.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Categories>> callback )
  {
    Backendless.Data.of( Categories.class ).find( query, callback );
  }
}
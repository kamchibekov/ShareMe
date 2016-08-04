package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Brands
{
  private java.util.Date created;
  private String objectId;
  private java.util.Date updated;
  private String name;
  private String ownerId;
  private String website;
  public java.util.Date getCreated()
  {
    return created;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getWebsite()
  {
    return website;
  }

  public void setWebsite( String website )
  {
    this.website = website;
  }

                                                    
  public Brands save()
  {
    return Backendless.Data.of( Brands.class ).save( this );
  }

  public Future<Brands> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Brands> future = new Future<Brands>();
      Backendless.Data.of( Brands.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Brands> callback )
  {
    Backendless.Data.of( Brands.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Brands.class ).remove( this );
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
      Backendless.Data.of( Brands.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Brands.class ).remove( this, callback );
  }

  public static Brands findById( String id )
  {
    return Backendless.Data.of( Brands.class ).findById( id );
  }

  public static Future<Brands> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Brands> future = new Future<Brands>();
      Backendless.Data.of( Brands.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Brands> callback )
  {
    Backendless.Data.of( Brands.class ).findById( id, callback );
  }

  public static Brands findFirst()
  {
    return Backendless.Data.of( Brands.class ).findFirst();
  }

  public static Future<Brands> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Brands> future = new Future<Brands>();
      Backendless.Data.of( Brands.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Brands> callback )
  {
    Backendless.Data.of( Brands.class ).findFirst( callback );
  }

  public static Brands findLast()
  {
    return Backendless.Data.of( Brands.class ).findLast();
  }

  public static Future<Brands> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Brands> future = new Future<Brands>();
      Backendless.Data.of( Brands.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Brands> callback )
  {
    Backendless.Data.of( Brands.class ).findLast( callback );
  }

  public static BackendlessCollection<Brands> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Brands.class ).find( query );
  }

  public static Future<BackendlessCollection<Brands>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Brands>> future = new Future<BackendlessCollection<Brands>>();
      Backendless.Data.of( Brands.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Brands>> callback )
  {
    Backendless.Data.of( Brands.class ).find( query, callback );
  }
}
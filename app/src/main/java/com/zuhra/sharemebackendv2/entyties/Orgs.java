package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Orgs
{
  private String name;
  private java.util.Date updated;
  private String objectId;
  private String address;
  private java.util.Date created;
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

  public String getObjectId()
  {
    return objectId;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress( String address )
  {
    this.address = address;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public Orgs save()
  {
    return Backendless.Data.of( Orgs.class ).save( this );
  }

  public Future<Orgs> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orgs> future = new Future<Orgs>();
      Backendless.Data.of( Orgs.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Orgs> callback )
  {
    Backendless.Data.of( Orgs.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Orgs.class ).remove( this );
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
      Backendless.Data.of( Orgs.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Orgs.class ).remove( this, callback );
  }

  public static Orgs findById( String id )
  {
    return Backendless.Data.of( Orgs.class ).findById( id );
  }

  public static Future<Orgs> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orgs> future = new Future<Orgs>();
      Backendless.Data.of( Orgs.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Orgs> callback )
  {
    Backendless.Data.of( Orgs.class ).findById( id, callback );
  }

  public static Orgs findFirst()
  {
    return Backendless.Data.of( Orgs.class ).findFirst();
  }

  public static Future<Orgs> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orgs> future = new Future<Orgs>();
      Backendless.Data.of( Orgs.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Orgs> callback )
  {
    Backendless.Data.of( Orgs.class ).findFirst( callback );
  }

  public static Orgs findLast()
  {
    return Backendless.Data.of( Orgs.class ).findLast();
  }

  public static Future<Orgs> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orgs> future = new Future<Orgs>();
      Backendless.Data.of( Orgs.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Orgs> callback )
  {
    Backendless.Data.of( Orgs.class ).findLast( callback );
  }

  public static BackendlessCollection<Orgs> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Orgs.class ).find( query );
  }

  public static Future<BackendlessCollection<Orgs>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Orgs>> future = new Future<BackendlessCollection<Orgs>>();
      Backendless.Data.of( Orgs.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Orgs>> callback )
  {
    Backendless.Data.of( Orgs.class ).find( query, callback );
  }
}
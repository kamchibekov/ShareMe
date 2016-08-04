package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class OrderStates
{
  private String name;
  private String objectId;
  private java.util.Date created;
  private String ownerId;
  private java.util.Date updated;
  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

                                                    
  public OrderStates save()
  {
    return Backendless.Data.of( OrderStates.class ).save( this );
  }

  public Future<OrderStates> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderStates> future = new Future<OrderStates>();
      Backendless.Data.of( OrderStates.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<OrderStates> callback )
  {
    Backendless.Data.of( OrderStates.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( OrderStates.class ).remove( this );
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
      Backendless.Data.of( OrderStates.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( OrderStates.class ).remove( this, callback );
  }

  public static OrderStates findById( String id )
  {
    return Backendless.Data.of( OrderStates.class ).findById( id );
  }

  public static Future<OrderStates> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderStates> future = new Future<OrderStates>();
      Backendless.Data.of( OrderStates.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<OrderStates> callback )
  {
    Backendless.Data.of( OrderStates.class ).findById( id, callback );
  }

  public static OrderStates findFirst()
  {
    return Backendless.Data.of( OrderStates.class ).findFirst();
  }

  public static Future<OrderStates> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderStates> future = new Future<OrderStates>();
      Backendless.Data.of( OrderStates.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<OrderStates> callback )
  {
    Backendless.Data.of( OrderStates.class ).findFirst( callback );
  }

  public static OrderStates findLast()
  {
    return Backendless.Data.of( OrderStates.class ).findLast();
  }

  public static Future<OrderStates> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderStates> future = new Future<OrderStates>();
      Backendless.Data.of( OrderStates.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<OrderStates> callback )
  {
    Backendless.Data.of( OrderStates.class ).findLast( callback );
  }

  public static BackendlessCollection<OrderStates> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( OrderStates.class ).find( query );
  }

  public static Future<BackendlessCollection<OrderStates>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<OrderStates>> future = new Future<BackendlessCollection<OrderStates>>();
      Backendless.Data.of( OrderStates.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<OrderStates>> callback )
  {
    Backendless.Data.of( OrderStates.class ).find( query, callback );
  }
}
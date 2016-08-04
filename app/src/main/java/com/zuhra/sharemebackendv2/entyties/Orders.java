package com.zuhra.sharemebackendv2.entyties;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class Orders
{
  private String objectId;
  private String ownerId;
  private java.util.Date start_time;
  private String title;
  private String note;
  private java.util.Date created;
  private java.util.Date updated;
  private java.util.Date end_time;
  private java.util.List<BackendlessUser> user_id;
  private java.util.List<com.zuhra.sharemebackendv2.entyties.OrderStates> state_id;
  private java.util.List<com.zuhra.sharemebackendv2.entyties.Orgs> org_id;
  private java.util.List<com.zuhra.sharemebackendv2.entyties.Products> product_id;
  public String getObjectId()
  {
    return objectId;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getStart_time()
  {
    return start_time;
  }

  public void setStart_time( java.util.Date start_time )
  {
    this.start_time = start_time;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getNote()
  {
    return note;
  }

  public void setNote( String note )
  {
    this.note = note;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getEnd_time()
  {
    return end_time;
  }

  public void setEnd_time( java.util.Date end_time )
  {
    this.end_time = end_time;
  }

  public java.util.List<BackendlessUser> getUser_id()
  {
    return user_id;
  }

  public void setUser_id( java.util.List<BackendlessUser> user_id )
  {
    this.user_id = user_id;
  }

  public java.util.List<com.zuhra.sharemebackendv2.entyties.OrderStates> getState_id()
  {
    return state_id;
  }

  public void setState_id( java.util.List<com.zuhra.sharemebackendv2.entyties.OrderStates> state_id )
  {
    this.state_id = state_id;
  }

  public java.util.List<com.zuhra.sharemebackendv2.entyties.Orgs> getOrg_id()
  {
    return org_id;
  }

  public void setOrg_id( java.util.List<com.zuhra.sharemebackendv2.entyties.Orgs> org_id )
  {
    this.org_id = org_id;
  }

  public java.util.List<com.zuhra.sharemebackendv2.entyties.Products> getProduct_id()
  {
    return product_id;
  }

  public void setProduct_id( java.util.List<com.zuhra.sharemebackendv2.entyties.Products> product_id )
  {
    this.product_id = product_id;
  }

                                                    
  public Orders save()
  {
    return Backendless.Data.of( Orders.class ).save( this );
  }

  public Future<Orders> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orders> future = new Future<Orders>();
      Backendless.Data.of( Orders.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Orders> callback )
  {
    Backendless.Data.of( Orders.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Orders.class ).remove( this );
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
      Backendless.Data.of( Orders.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Orders.class ).remove( this, callback );
  }

  public static Orders findById( String id )
  {
    return Backendless.Data.of( Orders.class ).findById( id );
  }

  public static Future<Orders> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orders> future = new Future<Orders>();
      Backendless.Data.of( Orders.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Orders> callback )
  {
    Backendless.Data.of( Orders.class ).findById( id, callback );
  }

  public static Orders findFirst()
  {
    return Backendless.Data.of( Orders.class ).findFirst();
  }

  public static Future<Orders> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orders> future = new Future<Orders>();
      Backendless.Data.of( Orders.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Orders> callback )
  {
    Backendless.Data.of( Orders.class ).findFirst( callback );
  }

  public static Orders findLast()
  {
    return Backendless.Data.of( Orders.class ).findLast();
  }

  public static Future<Orders> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Orders> future = new Future<Orders>();
      Backendless.Data.of( Orders.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Orders> callback )
  {
    Backendless.Data.of( Orders.class ).findLast( callback );
  }

  public static BackendlessCollection<Orders> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Orders.class ).find( query );
  }

  public static Future<BackendlessCollection<Orders>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Orders>> future = new Future<BackendlessCollection<Orders>>();
      Backendless.Data.of( Orders.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Orders>> callback )
  {
    Backendless.Data.of( Orders.class ).find( query, callback );
  }
}
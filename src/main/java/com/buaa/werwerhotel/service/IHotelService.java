package com.buaa.werwerhotel.service;

import com.buaa.werwerhotel.entity.Comment;
import com.buaa.werwerhotel.entity.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface IHotelService {
    List<String> searchHotelByCity(String city);


    List<Comment> searchComment(String id);


    List<String> getPlaces();

    List<Map<String, Object>> getAvailableRoom(String id, String arrive_date, String ldeparture_date);

    Integer getCommentNum(String id);

    Hotel getHotelInfo(String id);

    List<String> getHotelPhoto(String id);

    Double getHotelRank(String id);

    List<Map<String, Object>> getRoomDetail(String id, String arrive_date, String ldeparture_date);

    void addHotelorderDetail(String id, String oid, String checkinTime, String checkoutTime, Integer roomNum, String roomType, String customerName, String customerId);

    Map<String, String> getHotelName(String id);

    List<Map<String, Object>> getHotelOrderDetail(String oid);

    void updateNumWhenBill(String hotel_id,String checkin_time,String checkout_time,Integer room_num);
    void updateNumWhenCancel(String hotel_id,String checkin_time,String checkout_time,Integer room_num);

}

package sito.davide.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import sito.davide.dao.BusinessParameterDao;
import sito.davide.dto.OrderDTO;
import sito.davide.entity.TbBusinessParameter;
import sito.davide.entity.TbOrder;
import sito.davide.utils.exception.CustomException;

@Component
public class OrderUtils
{
	@Autowired
	BusinessParameterDao parameterDao;
	
 	public static long getOrderAgeInMinutes(TbOrder order)
 	{
 		Date orderCreationDate = order.getCreationDate();
		Date nowDate = new Date();
		
		long timeDeltaInMilliseconds = nowDate.getTime() - orderCreationDate.getTime();
	    long timeDeltaInMinutes = TimeUnit.MINUTES.convert(timeDeltaInMilliseconds,TimeUnit.MILLISECONDS);
	    
	    return timeDeltaInMinutes;
 	}
 	
 	public void validateOrderRequest(OrderDTO orderRequest) throws Exception
 	{
 		Optional<TbBusinessParameter> validQuantitiesOpt = parameterDao.findByNameIgnoreCase(EBusinessParameter.PILOTES_VALID_QUANTITIES.name());
		if(!validQuantitiesOpt.isPresent())
			throw new CustomException("Invalid parameter configuration");
		if(!Arrays.asList(validQuantitiesOpt.get().getStrVal().split(",")).contains(orderRequest.getNumberOfPilotes()+""))
			throw new CustomException("Invalid number of pilotes specified");
		if(!StringUtils.hasLength(orderRequest.getDeliveryAddress()))
			throw new CustomException("Invalid delivery address");
 	}
}

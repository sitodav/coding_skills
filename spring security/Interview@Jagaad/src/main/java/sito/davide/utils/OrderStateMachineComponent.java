package sito.davide.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sito.davide.dao.BusinessParameterDao;
import sito.davide.dao.OrderDao;
import sito.davide.entity.TbBusinessParameter;
import sito.davide.entity.TbOrder;
import sito.davide.utils.exception.CustomException;

/*Component used to simulate the state machine for orders */
/*NB: this can be a bottle neck but it's only for sake of simulation !!!!!! 
 * 
 */
@Component 
@Transactional(rollbackFor = Exception.class)
public class OrderStateMachineComponent
{

	private static final Logger log = LoggerFactory.getLogger(OrderStateMachineComponent.class);

	@Autowired
	OrderDao orderDao;

	@Autowired
	BusinessParameterDao parametersDao;

	@Scheduled(fixedDelayString = "${order.status.frequency.seconds}", timeUnit = TimeUnit.SECONDS)
	public void fixedRateJob()
	{
		try
		{
			List<TbOrder> createdOrders = orderDao.findOrdersInStatus(Arrays.asList(EOrderStatus.CREATED.name()));

			for (TbOrder order : createdOrders)
			{

				/* From CONFIRMED -> IN_PREPARATION (EVERY 5 minutes) */

				/* field mapping from client */
				Optional<TbBusinessParameter> validWindowForPreparation = parametersDao.findByNameIgnoreCase(EBusinessParameter.UPDATE_VALIDWINDOW_MNTS.name());
				if (!validWindowForPreparation.isPresent())
					throw new CustomException("Invalid parameter configuration");

				Double validWindowsAsminutes = Double.parseDouble(validWindowForPreparation.get().getStrVal());

				if (OrderUtils.getOrderAgeInMinutes(order) >= validWindowsAsminutes)
				{

					order.setOrderStatus(EOrderStatus.IN_PREPARATION.name());
					orderDao.save(order);
					log.info("Order " + order.getOrderNumber() + " is now IN PREPARATION (and cannot be updated from the user anymore)");
				}

			}
		}
		catch (Exception ex)
		{
			log.error("Error in scheduled component", ex);

		}

	}
}

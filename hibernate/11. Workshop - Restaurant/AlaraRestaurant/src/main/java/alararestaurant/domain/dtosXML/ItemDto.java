package alararestaurant.domain.dtosXML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDto {

	@XmlElement(name = "item")
	private List<BaseItemDto> allItems;
	
	public ItemDto() {
		this.allItems = new ArrayList<BaseItemDto>();
	}

	public List<BaseItemDto> getAllItems() {
		return allItems;
	}

	public void setAllItems(List<BaseItemDto> allItems) {
		this.allItems = allItems;
	}
}

package components.popup;

public interface IPopup<T> {

  T popupShouldBeVisible();

  T popupShouldNotVisible();
}

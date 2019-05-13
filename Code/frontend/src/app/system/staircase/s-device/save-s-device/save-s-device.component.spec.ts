import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveSDeviceComponent } from './save-s-device.component';

describe('SaveSDeviceComponent', () => {
  let component: SaveSDeviceComponent;
  let fixture: ComponentFixture<SaveSDeviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveSDeviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveSDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

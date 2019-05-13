import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SDeviceComponent } from './s-device.component';

describe('SDeviceComponent', () => {
  let component: SDeviceComponent;
  let fixture: ComponentFixture<SDeviceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SDeviceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveDeptComponent } from './save-dept.component';

describe('SaveDeptComponent', () => {
  let component: SaveDeptComponent;
  let fixture: ComponentFixture<SaveDeptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveDeptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveDeptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
